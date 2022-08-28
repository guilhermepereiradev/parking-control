package com.oxd.parkingcontrol.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(nullable = false, unique= true, length = 10)
    private String parkingSpotNumber;
    @OneToOne(mappedBy = "parkingSpot", cascade = CascadeType.ALL)
    private CarModel car;

    public ParkingSpotModel(){}

    public ParkingSpotModel(UUID id, String parkingSpotNumber, CarModel car) {
        this.id = id;
        this.parkingSpotNumber = parkingSpotNumber;
        this.car = car;
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
}
