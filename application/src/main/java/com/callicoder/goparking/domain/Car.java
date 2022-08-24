package com.callicoder.goparking.domain;

import java.util.Objects;

public class Car {

    private final String registrationNumber;
    private final String color;

    public Car(String registrationNumber, String color) {
        if (registrationNumber == null) {
            throw new IllegalArgumentException(
                "Registration number must not be null"
            );
        }

        if (color == null) {
            throw new IllegalArgumentException("Color must not be null");
        }

        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(registrationNumber, car.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}
