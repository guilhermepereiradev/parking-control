package com.oxd.parkingcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResponsibleRepository extends JpaRepository<ResponsibleRepository, UUID> {
}
