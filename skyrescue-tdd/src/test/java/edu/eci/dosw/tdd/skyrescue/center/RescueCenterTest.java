package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void shouldRegisterADroneWithAnEmptyId() {
        // Arrange
        RescueCenter center = new RescueCenter();
        Drone drone = new Drone("", "DJI Mini", 8);

        // Act
        boolean droneRegistered = center.addDrone(drone);

        // Assert
        assertTrue(registered);
        assertFalse(droneRegistered, "Can't add a drone with an empty id");
    }

    @Test
    void shouldNotRegisterNullDrone() {
        // Actuar
        boolean registered = center.addDrone(null);
        // Assert
        assertFalse(registered);
    void shouldNotRegisterTwoDronesWithSameId() {

    }

    // Casos B
    @Test
    void shouldNotHaveAnInexistentDrone() {

    }

    @Test
    void shouldShowADroneIsOccupied() {

    }

    // Casos C
    @Test
    void shouldNotCloseTheSameMissionTwoTimes() {

    }

    @Test
    void shouldNotModifyAnotherActiveMission() {

    }
}