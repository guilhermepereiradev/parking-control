package com.oxd.parkingcontrol.repositories;

import com.oxd.parkingcontrol.models.ResponsibleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResponsibleRepository extends JpaRepository<ResponsibleModel, UUID> {
    boolean existsByApartmentAndBlock(String apartment, String block);

    Optional<ResponsibleModel> findByEmail(String email);

    @Query(value = "SELECT * FROM tb_responsible WHERE apartment=:apartment AND block=:block", nativeQuery = true)
    Optional<ResponsibleModel> findByApartmentAndBlock(String apartment, String block);

}
