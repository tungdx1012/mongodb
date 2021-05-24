package com.example.trainer.dto;

import com.example.trainer.entities.Horse;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HorseResponse {
    private String id;
    private String name;
    private Date foaled;
    private List<TrainerResponse> trainers = new ArrayList<>();

    public List<TrainerResponse> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<TrainerResponse> trainers) {
        this.trainers = trainers;
    }

    public HorseResponse(String id, String name, Date foaled) {
        this.id = id;
        this.name = name;
        this.foaled = foaled;
    }
    public HorseResponse(Horse horse) {
        BeanUtils.copyProperties(horse, this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoaled() {
        return foaled;
    }

    public void setFoaled(Date foaled) {
        this.foaled = foaled;
    }
}
