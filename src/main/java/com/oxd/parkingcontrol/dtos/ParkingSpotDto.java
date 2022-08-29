package com.oxd.parkingcontrol.dtos;

import com.oxd.parkingcontrol.models.CarModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


public class ParkingSpotDto {

    @NotBlank
    private String parkingSpotNumber;

    @NotBlank
    private CarModel car;

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public void setCar(CarModel car) {
        this.car = car;
    }
}
