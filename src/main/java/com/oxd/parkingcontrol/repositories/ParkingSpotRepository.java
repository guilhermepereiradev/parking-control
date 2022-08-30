package com.oxd.parkingcontrol.repositories;

import com.oxd.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    @Query(value = "SELECT * FROM tb_parking_spot, tb_car WHERE tb_parking_spot.car_id=tb_car.id AND tb_car.license_plate=:carLicensePlate", nativeQuery = true)
    Optional<ParkingSpotModel> findByCarLicensePlate(String carLicensePlate);

    @Query(value = "SELECT * FROM tb_parking_spot WHERE registration_date BETWEEN :d1 AND :d2", nativeQuery = true)
    Optional<ParkingSpotModel> findByDateInterval(Date d1, Date d2);
}
