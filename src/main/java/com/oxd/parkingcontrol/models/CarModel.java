package com.oxd.parkingcontrol.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_CAR")
public class CarModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 7)
    private String licensePlate;
    @Column(nullable = false, length = 70)
    private String brand;
    @Column(nullable = false, length = 70)
    private String model;
    @Column(nullable = false, length = 70)
    private String color;

    @ManyToOne
    @JoinColumn(name = "responsible_id", nullable = false)
    private ResponsibleModel responsible;

    public CarModel() {}

    public CarModel(UUID id, String licensePlate, String brand, String model, String color) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
