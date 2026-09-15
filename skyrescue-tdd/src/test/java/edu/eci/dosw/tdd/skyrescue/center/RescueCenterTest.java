package edu.eci.dosw.tdd.skyrescue.center;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RescueCenterTest {
    // Casos A
    @Test
    void shouldRegisterADroneWithAnEmptyId() {
        // Arrange
        RescueCenter center = new RescueCenter();
        Drone drone = new Drone("", "DJI Mini", 8);

        // Act
        boolean droneRegistered = center.addDrone(drone);

        // Assert
        assertFalse(droneRegistered, "Can't add a drone with an empty id");
    }

    @Test
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