package com.oxd.parkingcontrol.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oxd.parkingcontrol.models.ParkingSpotModel;
import com.oxd.parkingcontrol.models.ResponsibleModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

public class CarDto {

    @NotBlank
    @Size(max = 7)
    private String licensePlate;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotBlank
    private String color;
    @NotBlank
    private ResponsibleModel responsible;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ResponsibleModel getResponsible() {
        return responsible;
    }

    public void setResponsible(ResponsibleModel responsible) {
        this.responsible = responsible;
    }
}
