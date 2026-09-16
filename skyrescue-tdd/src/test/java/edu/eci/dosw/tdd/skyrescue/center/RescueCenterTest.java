package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;

import edu.eci.dosw.tdd.skyrescue.center.RescueCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;

import edu.eci.dosw.tdd.skyrescue.mission.Mission;
import edu.eci.dosw.tdd.skyrescue.mission.MissionStatus;

public class RescueCenterTest {

    private RescueCenter center;

    @BeforeEach
    void setUp() {
        center = new RescueCenter();
    }

    // Casos A
    @Test
    void shouldRegisterDroneWhenDataIsValid() {
        // Preparar
        Drone drone = new Drone("D1", "Falcon", 50);
        // Actuar
        boolean registered = center.addDrone(drone);
        assertTrue(registered);
    }

    @Test
    void shouldNotRegisterNullDrone () {
        // Actuar
        boolean registered = center.addDrone(null);
        // Assert
        assertFalse(registered);
    }

    @Test
    void shouldNotRegisterADroneWithAnEmptyId () {
        // Preparar
        Drone drone = new Drone("", "DJI Mini", 8);

        // Actuar
        boolean droneRegistered = center.addDrone(drone);

        // Assert
        assertFalse(droneRegistered, "Can't add a drone with an empty id");
    }

    @Test
    void shouldNotRegisterTwoDronesWithSameId() {
        // Preparar
        Drone firstDrone = new Drone("D2", "E88", 10);
        Drone secondDrone = new Drone("D2", "DJI Neo 2", 7);

        // Actuar
        center.addDrone(firstDrone);
        boolean secondDroneRegistered = center.addDrone(secondDrone);

        // Assert
        assertFalse(secondDroneRegistered, "Can't add a drone with same ID");
    }

    @Test
    void shouldNotAssignMissionWithAnInexistentOperator() {
        // Preparar
        Drone drone = new Drone("D3", "E26", 15);
        center.addDrone(drone);

        // Actuar y Assert
        assertThrows(IllegalArgumentException.class, () -> center.assignMission("1005", "D3", "Zona norte", 10));
    }

    @Test
    void shouldNotAssignMissionWithAnOperatorWithActiveMission() {
        // Preparar
        RescueOperator operator = new RescueOperator("OP1", "Ana");
        center.addOperator(operator);

        Drone drone_1 = new Drone("D4", "E26", 15);
        center.addDrone(drone_1);

        Drone drone_2 = new Drone("D5", "E26", 15);
        center.addDrone(drone_2);

        center.assignMission("OP1", "D4", "Zona norte", 15);

        // Actuar y Assert
        assertThrows(IllegalStateException.class, () -> center.assignMission("OP1", "D5", "Zona sur", 15));


    }


    @Test
    void shouldCompleteActiveMission() {
        // Arrange
        RescueCenter center = new RescueCenter();
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




}