package com.example.trainer.repository;

import com.example.trainer.entities.Trainer;

import java.util.List;

public interface TrainerBaseRepository {
    List<Trainer> findTrainerByHorse();
}
