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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(nullable = false, unique = true, length = 7)
    private String licensePlate;
    @Column(nullable = false, length = 70)
    private String brand;
    @Column(nullable = false, length = 70)
    private String model;
    @Column(nullable = false, length = 70)
    private String color;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @JsonIgnore
    @OneToOne
    private ParkingSpotModel parkingSpot;

    @ManyToOne
    @JoinColumn(name = "responsible_id", nullable = false)
    private ResponsibleModel responsible;

    public CarModel() {}

    public CarModel(UUID id, String licensePlateCar, String brandCar, String modelCar, String colorCar, LocalDateTime registrationDate, ParkingSpotModel parkingSpot, ResponsibleModel responsible) {
        this.id = id;
        this.licensePlate = licensePlateCar;
        this.brand = brandCar;
        this.model = modelCar;
        this.color = colorCar;
        this.registrationDate = registrationDate;
        this.parkingSpot = parkingSpot;
        this.responsible = responsible;
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

    public void setLicensePlate(String licensePlateCar) {
        this.licensePlate = licensePlateCar;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brandCar) {
        this.brand = brandCar;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String modelCar) {
        this.model = modelCar;
    }

    public String getColor() {
        return color;
    }

    public void setColorCar(String colorCar) {
        this.color = colorCar;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public ParkingSpotModel getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpotModel parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public ResponsibleModel getResponsible() {
        return responsible;
    }

    public void setResponsible(ResponsibleModel responsible) {
        this.responsible = responsible;
    }
}
