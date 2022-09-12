package com.oxd.parkingcontrol.repositories;

import com.oxd.parkingcontrol.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRespository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByUsername(String username);
}
