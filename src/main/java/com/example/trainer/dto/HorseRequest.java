package com.example.trainer.dto;

import java.util.Date;

public class HorseRequest {
    private String id;
    private String name;
    private Date foaled;

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
