package com.railway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.railway.entity.Train;

import java.util.List;

public interface TrainRepo extends JpaRepository<Train, Long> {
    List<Train> findBySourceAndDestination(String source, String destination);
}
