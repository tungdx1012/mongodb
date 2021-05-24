package com.example.trainer.dto;

import com.example.trainer.entities.Horse;
import com.example.trainer.entities.Trainer;
import org.springframework.beans.BeanUtils;

import java.util.List;


public class TrainerRespone {
    private String id;
    private String account_id;
    private String name;
    private List<HorseResponse> listHorse;

    public TrainerRespone(String id, String account_id, String name) {
        this.id = id;
        this.account_id = account_id;
        this.name = name;
    }

    public List<HorseResponse> getListHorse() {
        return listHorse;
    }

    public void setListHorse(List<HorseResponse> listHorse) {
        this.listHorse = listHorse;
    }

    public TrainerRespone(Trainer trainer) {
        BeanUtils.copyProperties(trainer, this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
