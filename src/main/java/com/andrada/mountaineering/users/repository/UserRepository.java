package com.andrada.mountaineering.users.repository;

import com.andrada.mountaineering.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT COUNT(u) FROM User u inner join u.roles r WHERE r.role like 'ROLE_ADMIN'")
    long getAdminUserCount();

}
