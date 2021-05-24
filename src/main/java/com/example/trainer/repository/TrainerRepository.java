package com.example.trainer.repository;

import com.example.trainer.entities.Trainer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TrainerRepository extends MongoRepository<Trainer, String>, TrainerBaseRepository {
    List<Trainer> findAllByIdIn(Set<String> ids);



}
