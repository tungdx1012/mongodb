package com.example.trainer.service;

import com.example.trainer.dto.TrainerRequest;
import com.example.trainer.dto.TrainerResponse;
import com.example.trainer.entities.Trainer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrainerService {
    public List<Trainer> getAllTrainers();

    public TrainerResponse createTrainer(TrainerRequest request);

    public Trainer getTrainerById(String id);

    public TrainerResponse updateTrainer(String Id, Trainer trainerDetails, TrainerRequest request);

    public ResponseEntity<?> deleteTrainer(String Id);

    public List<TrainerResponse> findTrainerByTotalHorse(int totalHorse);


}
