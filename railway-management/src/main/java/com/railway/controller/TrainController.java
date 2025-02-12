package com.railway.controller;

import com.railway.entity.Train;
import com.railway.service.TrainService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController	
@RequestMapping("/api/admin")
public class TrainController {
	private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping("/train")
    public Map<String, String> addTrain(@RequestBody Train train) {
        trainService.addTrain(train);
        return Map.of("message", "Train added successfully");
    }

    @PutMapping("/train/{trainId}")
    public Map<String, String> updateTrain(@PathVariable Long trainId, @RequestBody Map<String, Integer> request) {
        trainService.updateTrain(trainId, request.get("totalSeats"));
        return Map.of("message", "Train updated successfully");
    }


}
