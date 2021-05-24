package com.example.trainer.controller;

import com.example.trainer.ApiConst;
import com.example.trainer.dto.*;
import com.example.trainer.entities.Trainer;
import com.example.trainer.service.TrainerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainerController {
    @Autowired
    TrainerServiceImpl trainerServiceImpl;

    // Get All Trainers
    @GetMapping(ApiConst.GET_ALL_TRAINER)
    public List<Trainer> getAllTrainers() {
        return trainerServiceImpl.getAllTrainers();
    }

    // Create a new Trainer
    @PostMapping(ApiConst.TRAINER)
    public TrainerResponse createTrainer(@RequestBody TrainerRequest request) {
        return trainerServiceImpl.createTrainer(request);
    }

    // Get a Single Trainer
    @GetMapping(ApiConst.TRAINER)
    public Trainer getTrainerById(@RequestParam(value = "id") String id) {
        return trainerServiceImpl.getTrainerById(id);
    }

    // Update a Trainer
    @PutMapping(ApiConst.TRAINER)
    public TrainerResponse updateTrainer(@RequestParam(value = "id") String id,
                                         @RequestBody Trainer trainerDetails, TrainerRequest request) {
        return trainerServiceImpl.updateTrainer(id, trainerDetails, request);
    }

    // Delete a Trainer
    @DeleteMapping(ApiConst.TRAINER)
    public ResponseEntity<?> deleteTrainer(@RequestParam(value = "id") String id) {
        return trainerServiceImpl.deleteTrainer(id);
    }

    //Get Trainer Has Horse > 3
    @GetMapping(ApiConst.TRAINER_HORSE)
    @ResponseBody
    public ResponseEntity<List<TrainerResponse>> getTrainerByHorse(TrainerFilter request) {
        List<TrainerResponse> response = trainerServiceImpl.findTrainerByTotalHorse(request.getTotalHorse());
        return new ResponseEntity<>(response, new HttpHeaders() , HttpStatus.OK);
    }
}
