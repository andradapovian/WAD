package com.andrada.mountaineering.users.repository;

import com.andrada.mountaineering.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
