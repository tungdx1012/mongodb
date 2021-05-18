package com.example.trainer.dto;

import com.example.trainer.entities.Horse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class HorseResponse {
    private Integer id;
    private String name;
    private Date foaled;

    public HorseResponse(String name, Date foaled) {
        this.name = name;
        this.foaled = foaled;
    }

    public HorseResponse(Horse horse) {
        this.id = horse.getId();
        this.name = horse.getName();
        this.foaled = horse.getFoaled();
    }
    public HorseResponse(Integer id, String name, Date foaled) {
        this.id = id;
        this.name = name;
        this.foaled = foaled;
    }
}
