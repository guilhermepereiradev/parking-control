package com.oxd.parkingcontrol.repositories;

import com.oxd.parkingcontrol.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarModel, UUID> {

    boolean existsByLicensePlate(String licensePlate);

    Optional<CarModel> findByLicensePlate(String licensePlate);
}
