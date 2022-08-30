package com.oxd.parkingcontrol.dtos;

import com.oxd.parkingcontrol.models.ResponsibleModel;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
}
