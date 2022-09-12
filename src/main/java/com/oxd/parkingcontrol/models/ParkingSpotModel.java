package com.oxd.parkingcontrol.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique= true, length = 10)
    private String parkingSpotNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", unique = true)
    private CarModel car;
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    public ParkingSpotModel(){}

    public ParkingSpotModel(UUID id, String parkingSpotNumber, LocalDateTime registrationDate) {
        this.id = id;
        this.parkingSpotNumber = parkingSpotNumber;
        this.registrationDate = registrationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public CarModel getCar() {
        return car;
    }

    public void setCar(CarModel car) {
        this.car = car;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
