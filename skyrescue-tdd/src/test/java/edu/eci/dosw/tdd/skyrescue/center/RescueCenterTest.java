package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import edu.eci.dosw.tdd.skyrescue.mission.Mission;

import edu.eci.dosw.tdd.skyrescue.mission.MissionStatus;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RescueCenterTest {

    private RescueCenter center;

    @BeforeEach
    void setUp() {
        center = new RescueCenter();
    }

    // Section A
    @Test
    void shouldRegisterDroneWhenDataIsValid() {
        // Arrange
        Drone drone = new Drone("D1", "Falcon", 50);
        boolean registered = center.addDrone(drone);
        assertTrue(registered);
    }

    @Test
    void shouldNotRegisterNullDrone () {
        boolean registered = center.addDrone(null);
        // Assert
        assertFalse(registered);
    }

    @Test
    void shouldNotRegisterADroneWithAnEmptyId () {
        // Arrange
        Drone drone = new Drone("", "DJI Mini", 8);

        // Act
        boolean droneRegistered = center.addDrone(drone);

        // Assert
        assertFalse(droneRegistered, "Can't add a drone with an empty id");
    }

    @Test
    void shouldNotRegisterTwoDronesWithSameId() {
        // Arrange
        Drone firstDrone = new Drone("D2", "E88", 10);
        Drone secondDrone = new Drone("D2", "DJI Neo 2", 7);

        // Act
        center.addDrone(firstDrone);
        boolean secondDroneRegistered = center.addDrone(secondDrone);

        // Assert
        assertFalse(secondDroneRegistered, "Can't add a drone with same ID");
    }

    @Test
    void shouldNotRegisterADroneWithABlankId() {
        // Arrange
        Drone drone = new Drone("   ", "Matrice 30", 12);

        // Act
        boolean registered = center.addDrone(drone);

        // Assert
        assertFalse(registered, "Can't add a drone with a blank id");
    }

    // Section B
    @Test
    void shouldNotHaveAnInexistentDrone() {
        // Arrange
        RescueOperator operator = new RescueOperator("R1", "Daniel");
        center.addOperator(operator);
        String inexistentDroneId = "D4";

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            center.assignMission("R1", inexistentDroneId, "Panama", 100);
        }, "An Illegal Argument must have been thrown because the drone id doesn't exists");
    }

    @Test
    void shouldNotAssignMissionIfADroneIsOccupied() {
        // Arrange
        RescueOperator firstOperator = new RescueOperator("R2", "Juan");
        RescueOperator secondOperator = new RescueOperator("R3", "David");
        Drone drone = new Drone("D33", "E88", 10);

        center.addOperator(firstOperator);
        center.addOperator(secondOperator);
        center.addDrone(drone);

        center.assignMission("R2", "D33", "Chia", 10);

        // Act and assert
        assertThrows(IllegalStateException.class, () ->{ center.assignMission("R3", "D33",
                "San Cristobal", 90);
        }, "An Illegal State must be thrown because the drone is assigned to a mission already");
    }

    @Test
    void shouldNotAssignMissionWithAnInexistentOperator() {
        // Arrange
        Drone drone = new Drone("D3", "E26", 15);
        center.addDrone(drone);

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> center.assignMission("1005", "D3", "Zona norte", 10));
    }

    @Test
    void shouldNotAssignMissionWithAnOperatorWithActiveMission() {
        // Arrange
        RescueOperator operator = new RescueOperator("OP1", "Ana");
        center.addOperator(operator);

        Drone drone_1 = new Drone("D4", "E26", 15);
        center.addDrone(drone_1);

        Drone drone_2 = new Drone("D5", "E26", 15);
        center.addDrone(drone_2);

        center.assignMission("OP1", "D4", "Zona norte", 15);

        // Act and assert
        assertThrows(IllegalStateException.class, () -> center.assignMission("OP1", "D5", "Zona sur", 15));
    }

    @Test
    void shouldAssignMissionWhenOperatorAndDroneAreValid() {
        // Arrange
        RescueOperator operator = new RescueOperator("R10", "Camila");
        Drone drone = new Drone("D10", "Falcon", 50);
        center.addOperator(operator);
        center.addDrone(drone);

        // Act
        Mission mission = center.assignMission("R10", "D10", "Usaquen", 30);

        // Assert
        assertNotNull(mission);
        assertEquals(MissionStatus.ACTIVE, mission.getStatus());
        assertFalse(drone.isAvailable());
    }

    @Test
    void shouldNotAssignMissionWhenDistanceExceedsDroneRange() {
        // Arrange
        RescueOperator operator = new RescueOperator("R11", "Mateo");
        Drone drone = new Drone("D11", "E88", 20);
        center.addOperator(operator);
        center.addDrone(drone);

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            center.assignMission("R11", "D11", "Suba", 50);
        }, "An Illegal Argument must have been thrown because the distance exceeds the drone max range");
    }

    // Section C
    @Test
    void shouldCompleteActiveMission() {
        // Arrange
        center.addOperator(new RescueOperator("OP2", "Luis"));
        Drone drone = new Drone("D5", "Falcon", 20);
        center.addDrone(drone);
        Mission mission = center.assignMission("OP2", "D5", "Zona Este", 10);

        // Act
        Mission completed = center.completeMission(mission.getId());

        // Assert
        assertEquals(MissionStatus.COMPLETED, completed.getStatus());
        assertNotNull(completed.getEndDate());
        assertTrue(drone.isAvailable());
    }

    @Test
    void shouldNotCompleteNonExistentMission() {
        assertThrows(
                IllegalArgumentException.class,
                () -> center.completeMission("MISSION NOT FOUND")
        );
    }

    @Test
    void shouldRejectBlankMissionIdWithAValidationMessage() {
        // Act
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> center.completeMission("   ")
        );

        // Assert
        assertEquals("Mission id must not be null or blank", exception.getMessage());
    }

    @Test
    void shouldNotCloseTheSameMissionTwoTimes() {
        // Arrange
        RescueOperator operator = new RescueOperator("R52", "Laura");
        Drone drone = new Drone("D52", "Falcon", 20);
        center.addOperator(operator);
        center.addDrone(drone);
        Mission mission = center.assignMission("R52", "D52", "Boyaca", 20);

        // Act
        center.completeMission(mission.getId());

        // Assert
        assertThrows(IllegalStateException.class, () -> {
            center.completeMission(mission.getId());
        }, "Should throw an state exception if the completed mission is tried to be closed");
    }

    @Test
    void shouldNotModifyAnotherActiveMission() {
        // Arrange
        RescueOperator firstOperator = new RescueOperator("R53", "Segundo");
        RescueOperator secondOperator = new RescueOperator("R54", "Allison");

        Drone firstDrone = new Drone("D53", "DJI Mini", 8);
        Drone secondDrone = new Drone ("D54", "E88", 10);

        center.addOperator(firstOperator);
        center.addOperator(secondOperator);
        center.addDrone(firstDrone);
        center.addDrone(secondDrone);

        Mission firstMission = center.assignMission("R53", "D53", "Usaquen", 8);
        Mission secondMission = center.assignMission("R54", "D54", "Suba", 10);

        // Act
        center.completeMission(firstMission.getId());

        // Assert
        assertEquals(MissionStatus.ACTIVE, secondMission.getStatus(), "The second mission status should be ACTIVE");
        assertFalse(secondDrone.isAvailable(), "The second mission drone must be inactive");
    }
}
