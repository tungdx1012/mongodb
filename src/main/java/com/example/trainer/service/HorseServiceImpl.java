package com.example.trainer.service;

import com.example.trainer.TrainerNotFoundException;
import com.example.trainer.dto.HorseFilter;
import com.example.trainer.dto.HorseRequest;
import com.example.trainer.dto.HorseResponse;
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

//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HorseServiceImpl implements HorseService {
    @Autowired
    private HorseRepository horseRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public List<Horse> getAllHorses() {
        return horseRepository.findAll();
    }

    @Override
    public List<Horse> findByIdAndFoaled(HorseFilter request) {
//        return horseRepository.findAll(new Specification<Horse>() {
//            @Override
//            public Predicate toPredicate(Root<Horse> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicates = new ArrayList<>();
//                if (trainerId != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),  trainerId )));
//                }
//                if (foaled != null) {
//                    System.out.println(root.get("foaled"));
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("foaled"), foaled)));
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        });
        Date fromDate = null;
        Date toDate = null;

        if (request.getFoaled() != null) {
            LocalDate localStart = LocalDate.of(request.getFoaled(), 1, 1);
            LocalDate localEnd = LocalDate.of(request.getFoaled() + 1, 1, 1);
            fromDate = Date.from(localStart.atStartOfDay().atZone(ZoneId.of("UTC")).toInstant());
            toDate = Date.from(Instant.ofEpochMilli(localEnd.atStartOfDay().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli() - 1));
        }

        return horseRepository.findByPrams(fromDate, toDate, request.getTrainerIds());
    }

    @Override
    public HorseResponse createHorse(HorseRequest request) {
        Horse horse = new Horse();
        BeanUtils.copyProperties(request, horse);
        horseRepository.save(horse);
        return new HorseResponse(horse);
    }

    @Override
    public HorseResponse updateHorse(String Id, Horse horseDetails) {
        Horse t = horseRepository.findById(Id)
                .orElseThrow(() -> new TrainerNotFoundException("Horse", "id", Id));
        t.setName(horseDetails.getName());
        t.setFoaled(horseDetails.getFoaled());
        Horse updatedHorse = horseRepository.save(t);
        return new HorseResponse(updatedHorse);
    }

    @Override
    public ResponseEntity<?> deleteHorse(String Id) {
        Horse t = horseRepository.findById(Id)
                .orElseThrow(() -> new TrainerNotFoundException("Horse", "id", Id));
        horseRepository.delete(t);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<HorseResponse> findDuplicateHorseAndTrainer() {
        //Find by Name
        List<Horse> horses = horseRepository.findAll();
        Map<String, List<Horse>> mapHorse = horses.stream().collect(Collectors.groupingBy(Horse::getName));
        Set<String> dupHorseIds = new HashSet<>();
        //Check empty and size >=2
        for (String horse : mapHorse.keySet()) {
            List<Horse> horseList = mapHorse.get(horse);
            if (!CollectionUtils.isEmpty(horseList) && horseList.size() >= 2) {
                dupHorseIds.add(horse);
            }
        }
        //Check by contain
        List<Horse> dupHorses = horses.stream().filter(h -> dupHorseIds.contains(h.getName())).collect(Collectors.toList());

        //Add to trainerIds
        Set<String> trainerIds = new HashSet<>();
        for (Horse h : dupHorses) {
            trainerIds.addAll(h.getTrainerIds());
        }

        //Find all by id
        List<Trainer> trainers = trainerRepository.findAllByIdIn(trainerIds);
        Map<String, TrainerResponse> mapTrainer = trainers.stream().collect(Collectors.toMap(Trainer::getId, TrainerResponse::new));

        List<HorseResponse> responses = new ArrayList<>();
        for (Horse horse : dupHorses) {
            HorseResponse horseResponse = new HorseResponse(horse);
            for (String trainerId : horse.getTrainerIds()) {
                horseResponse.getTrainers().add(mapTrainer.get(trainerId));
            }
            responses.add(horseResponse);
        }
        return responses;
    }
}
