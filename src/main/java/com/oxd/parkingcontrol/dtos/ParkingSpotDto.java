package com.oxd.parkingcontrol.dtos;

import com.oxd.parkingcontrol.models.CarModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


public class ParkingSpotDto {

    @NotBlank
    private String parkingSpotNumber;

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

}
