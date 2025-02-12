package com.railway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.railway.entity.User;

public interface Repository extends JpaRepository<User,Long> {
	Optional<User> findByUsername(String username);
	

}
