package com.oxd.parkingcontrol.repositories;

import com.oxd.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarRepository, UUID> {
}
