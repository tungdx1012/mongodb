package com.example.trainer.service;

import com.example.trainer.TrainerNotFoundException;
import com.example.trainer.dto.HorseResponse;
import com.example.trainer.dto.TrainerRequest;
import com.example.trainer.dto.TrainerResponse;
import com.example.trainer.entities.Horse;
import com.example.trainer.entities.Trainer;
import com.example.trainer.repository.HorseRepository;
import com.example.trainer.repository.TrainerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private HorseRepository horseRepository;

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public TrainerResponse createTrainer(TrainerRequest request) {
        Trainer trainer = new Trainer();
        BeanUtils.copyProperties(request, trainer);
        trainerRepository.save(trainer);
        return new TrainerResponse(trainer);
    }

    @Override
    public Trainer getTrainerById(String id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException("Trainer", "id", id));
    }

    @Override
    public TrainerResponse updateTrainer(String id, Trainer trainerDetails, TrainerRequest request) {
//        Trainer trainer = new Trainer();
        Trainer t = trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException("Trainer", "id", id));
        t.setName(trainerDetails.getName());
        t.setAccount_id(trainerDetails.getAccount_id());
//        BeanUtils.copyProperties(request, trainer);
        Trainer updatedTrainer = trainerRepository.save(t);
        return new TrainerResponse(updatedTrainer);
    }

    @Override
    public ResponseEntity<?> deleteTrainer(String id) {
        Trainer t = trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException("Trainer", "id", id));
        trainerRepository.delete(t);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<TrainerResponse> findTrainerByTotalHorse(int totalHorse) {
        //Find Horse by TrainerID
        List<Horse> horses = horseRepository.findAll().stream().filter(h -> !CollectionUtils.isEmpty(h.getTrainerIds())).collect(Collectors.toList());
        Set<String> trainerIds = new HashSet<>();
        for (Horse h : horses) {
            trainerIds.addAll(h.getTrainerIds());
        }

        Map<String, List<HorseResponse>> mapHorse = new HashMap<>();
        for (String trainerId : trainerIds) {
            List<Horse> horseList = horses.stream().filter(h -> h.getTrainerIds().contains(trainerId)).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(horseList) && horseList.size() >= totalHorse) {
                List<HorseResponse> horseResponses = new ArrayList<>();
                for (Horse h : horseList) {
                    horseResponses.add(new HorseResponse(h));
                }
                mapHorse.put(trainerId, horseResponses);
            }
        }

        //Find Trainer By TrainerID
        List<Trainer> trainers = trainerRepository.findAllByIdIn(mapHorse.keySet());
        //List Trainer By Horse
        List<TrainerResponse> respones = new ArrayList<>();
        for (Trainer trainer : trainers) {
            TrainerResponse trainerRespone = new TrainerResponse(trainer);
            trainerRespone.setListHorse(mapHorse.get(trainer.getId()));
            respones.add(trainerRespone);
        }
        return respones;
    }
}
