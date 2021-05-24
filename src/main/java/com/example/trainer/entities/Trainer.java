package com.example.trainer.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "trainer")
public class Trainer implements Serializable {
    @Id
    private String id;

    @Field(name = "account_id")
    private String account_id;
    @Field(name = "name")
    private String name;

    public Trainer() {
    }

    public Trainer(String id, String account_id, String name) {
        this.id = id;
        this.account_id = account_id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", account_id=" + account_id +
                ", name='" + name + '\'' +
                '}';
    }
}
