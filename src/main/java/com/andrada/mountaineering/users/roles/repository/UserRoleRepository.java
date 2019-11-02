package com.andrada.mountaineering.users.roles.repository;

import com.andrada.mountaineering.users.roles.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("SELECT u FROM UserRole u WHERE u.role LIKE :role")
    Optional<UserRole> findRoleByName(@Param("role") String role);

}
