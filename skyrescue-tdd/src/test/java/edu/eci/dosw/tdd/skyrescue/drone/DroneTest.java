package edu.eci.dosw.tdd.skyrescue.drone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DroneTest {

    @Test
    void shouldReturnModelFromGetter() {
        // Arrange
        Drone drone = new Drone("D1", "Falcon", 50);

        // Act & Assert
        assertEquals("Falcon", drone.getModel());
    }

    @Test
    void shouldBeEqualToItself() {
        // Arrange
        Drone drone = new Drone("D1", "Falcon", 50);

        // Act & Assert
        assertEquals(drone, drone);
    }

    @Test
    void shouldNotBeEqualToNullOrDifferentType() {
        // Arrange
        Drone drone = new Drone("D1", "Falcon", 50);

        // Act & Assert
        assertNotEquals(drone, null);
        assertNotEquals(drone, "D1");
    }

    @Test
    void shouldBeEqualWhenIdsMatch() {
        // Arrange
        Drone first = new Drone("D1", "Falcon", 50);
        Drone second = new Drone("D1", "DJI Mini", 8);

        // Act & Assert
        assertEquals(first, second);
    }

    @Test
    void shouldNotBeEqualWhenIdsDiffer() {
        // Arrange
        Drone first = new Drone("D1", "Falcon", 50);
        Drone second = new Drone("D2", "Falcon", 50);

        // Act & Assert
        assertNotEquals(first, second);
    }

    @Test
    void shouldHaveSameHashCodeWhenIdsMatch() {
        // Arrange
        Drone first = new Drone("D1", "Falcon", 50);
        Drone second = new Drone("D1", "DJI Mini", 8);

        // Act & Assert
        assertEquals(first.hashCode(), second.hashCode());
    }
}
