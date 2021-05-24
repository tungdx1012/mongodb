package com.example.trainer.repository;

import com.example.trainer.entities.Horse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface HorseBaseRepository {
    List<Horse> findByPrams(Date fromDate, Date toDate, Set<String> trainerIds);

}
