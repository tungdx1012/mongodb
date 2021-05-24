package com.example.trainer.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "horse")
public class Horse implements Serializable {
    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "foaled")
    private Date foaled;
    @Field("trainerIds")
    private Set<String> trainerIds = new HashSet<>();

    public Horse() {
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

    public Set<String> getTrainerIds() {
        return trainerIds;
    }

    public void setTrainerIds(Set<String> trainerIds) {
        this.trainerIds = trainerIds;
    }
}
