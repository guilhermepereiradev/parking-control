package com.oxd.parkingcontrol.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_RESPONSILBE")
public class ResponsibleModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(nullable = false, length = 130)
    private String name;
    @Column(nullable = false, length = 30)
    private String apartment;
    @Column(nullable = false, length = 30)
    private String block;
    @JsonIgnore
    @OneToMany(mappedBy = "responsible")
    private Set<CarModel> cars = new HashSet<>();

    public ResponsibleModel() {}

    public ResponsibleModel(UUID id, String name, String apartment, String block, Set<CarModel> cars) {
        this.id = id;
        this.name = name;
        this.apartment = apartment;
        this.block = block;
        this.cars = cars;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Set<CarModel> getCars() {
        return cars;
    }
}
