package com.railway.service;

import com.railway.entity.Train;
import com.railway.repository.TrainRepo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainService {
    private final TrainRepo trainRepository;

    public TrainService(TrainRepo trainRepository) {
        this.trainRepository = trainRepository;
    }

    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    public Train updateTrain(Long trainId, int totalSeats) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found"));
        train.setTotalSeats(totalSeats);
        return trainRepository.save(train);
    }

    public List<Train> getTrains(String source, String destination) {
        return trainRepository.findBySourceAndDestination(source, destination);
    }
}
