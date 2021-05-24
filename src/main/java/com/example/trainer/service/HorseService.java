package com.example.trainer.service;

import com.example.trainer.dto.HorseFilter;
import com.example.trainer.dto.HorseRequest;
import com.example.trainer.dto.HorseResponse;
import com.example.trainer.entities.Horse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HorseService {
    public List<Horse> getAllHorses();

    public List<Horse> findByIdAndFoaled(HorseFilter request);

    public HorseResponse createHorse(HorseRequest request);

    public HorseResponse updateHorse(String Id, Horse horseDetails);

    public ResponseEntity<?> deleteHorse(String Id);

    public List<HorseResponse> findDuplicateHorseAndTrainer();

}
