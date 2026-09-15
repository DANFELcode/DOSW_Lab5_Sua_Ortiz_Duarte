package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RescueCenterTest {

    private RescueCenter center;

    @BeforeEach
    void setUp() {
        center = new RescueCenter();
    }

    @Test
    void shouldRegisterDroneWhenDataIsValid() {
        // Preparar
        Drone drone = new Drone("D1", "Falcon", 50);
        // Actuar
        boolean registered = center.addDrone(drone);
        // Assert
        assertTrue(registered);
    }

    @Test
    void shouldNotRegisterNullDrone() {
        // Actuar
        boolean registered = center.addDrone(null);
        // Assert
        assertFalse(registered);
    }
}
