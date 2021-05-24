package com.example.trainer.dto;

import java.util.HashSet;
import java.util.Set;

public class HorseFilter {
    Set<String> trainerIds = new HashSet<>();
    Integer foaled;

    public Set<String> getTrainerIds() {
        return trainerIds;
    }

    public void setTrainerIds(Set<String> trainerIds) {
        this.trainerIds = trainerIds;
    }

    public Integer getFoaled() {
        return foaled;
    }

    public void setFoaled(Integer foaled) {
        this.foaled = foaled;
    }
}
