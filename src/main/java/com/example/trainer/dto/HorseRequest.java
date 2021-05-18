package com.example.trainer.dto;

import lombok.Data;
import java.util.Date;

@Data
public class HorseRequest {
    private Integer id;
    private String name;
    private Date foaled;
}
