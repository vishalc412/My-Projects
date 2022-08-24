package com.callicoder.goparking.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.callicoder.goparking.domain.Car;
import org.junit.jupiter.api.Test;

public class CarTests {

    @Test
    public void createCarWithNoRegistrationNo_shouldThrowError() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Car car = new Car(null, "White");
            }
        );
    }

    @Test
    public void createCarWithNoColor_shouldThrowError() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Car car = new Car("KA01HQ5467", null);
            }
        );
    }

    @Test
    public void compareTo_shouldCompareCarsByRegistrationNo() {
        Car car1 = new Car("KA01HQ4669", "White");
        Car car2 = new Car("KA01HQ4669", "Black");

        assertEquals(car1, car2);
    }
}
