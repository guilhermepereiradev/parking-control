package com.oxd.parkingcontrol.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oxd.parkingcontrol.models.CarModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ResponsibleDto {

    @NotBlank
    @Size(max = 130)
    private String name;
    @NotBlank
    @Size(max = 30)
    private String apartment;
    @NotBlank
    @Size(max = 30)
    private String block;
    @Column(nullable = false)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
