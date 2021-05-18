package com.example.trainer.service;

import com.example.trainer.dto.HorseRequest;
import com.example.trainer.dto.HorseResponse;
import com.example.trainer.entities.Horse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface HorseService {
    public List<Horse> getAllHorses();

    public List<Horse> findByIDandFoaled(Integer trainerID, Integer foaled);

    public HorseResponse createHorse(HorseRequest request);

    public HorseResponse updateHorse(@PathVariable(value = "id") Integer Id, @RequestBody Horse horseDetails);

    public ResponseEntity<?> deleteHorse(@PathVariable(value = "id") Integer Id);
}
