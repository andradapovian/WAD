package com.andrada.mountaineering.users.repository;

import com.andrada.mountaineering.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT COUNT(u) FROM User u inner join u.roles r WHERE r.role like 'ROLE_ADMIN'")
    long getAdminUserCount();

    @Query("SELECT COUNT(u) FROM User u inner join u.roles r WHERE r.role like 'ROLE_VIEWER'")
    long getViewerUserCount();

    @Query("SELECT u FROM User u WHERE u.username like :username")
    Optional<User> findUserByUsername(@Param("username") String username);

}

