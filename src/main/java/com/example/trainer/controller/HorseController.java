package com.example.trainer.controller;

import com.example.trainer.ApiConst;
import com.example.trainer.dto.HorseFilter;
import com.example.trainer.dto.HorseRequest;
import com.example.trainer.dto.HorseResponse;
import com.example.trainer.entities.Horse;
import com.example.trainer.repository.HorseRepository;
import com.example.trainer.service.HorseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HorseController {
    @Autowired
    HorseServiceImpl horseServiceImpl;

    // Get All Horses
    @GetMapping(ApiConst.GET_ALL)
    public Object getAllHorses() {
        return horseServiceImpl.getAllHorses();
    }

    @GetMapping("duphorse")
    public Object dupHorse() {
        return horseServiceImpl.findDuplicateHorseAndTrainer();
    }

//     Get : Filter by Trainer_ID and Year
    @GetMapping(ApiConst.HORSE)
    @ResponseBody
    public ResponseEntity<List<Horse>> getHorseByIdAndYear(HorseFilter request) {
        List<Horse> response = horseServiceImpl.findByIdAndFoaled(request);
        return new ResponseEntity<>(response, new HttpHeaders() ,HttpStatus.OK);
    }

    // Create a new Horse
    @PostMapping(value = "/horse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HorseResponse createHorse(@RequestBody HorseRequest request) {
        return horseServiceImpl.createHorse(request);
    }

    // Update a Trainer
    @PutMapping(ApiConst.HORSE)
    public HorseResponse updateHorse(@RequestParam(value = "id") String id,
                                     @RequestBody Horse horseDetails) {
        return horseServiceImpl.updateHorse(id, horseDetails);
    }


    // Delete a Horse
    @DeleteMapping(ApiConst.HORSE)
    public ResponseEntity<?> deleteHorse(@RequestParam(value = "id") String Id) {
        return horseServiceImpl.deleteHorse(Id);
    }
}

