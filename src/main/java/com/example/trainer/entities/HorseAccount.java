package com.example.trainer.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "horse_account")
public class HorseAccount implements Serializable {
    @Id
    private Integer id;
    private Integer horse_id;
    private Integer account_id;
    private Integer archive;

    public HorseAccount() {
    }

    public HorseAccount(Integer id, Integer horse_id, Integer account_id, Integer archive) {
        this.id = id;
        this.horse_id = horse_id;
        this.account_id = account_id;
        this.archive = archive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHorse_id() {
        return horse_id;
    }

    public void setHorse_id(Integer horse_id) {
        this.horse_id = horse_id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getArchive() {
        return archive;
    }

    public void setArchive(Integer archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "HorseAccount{" +
                "id=" + id +
                ", horse_id=" + horse_id +
                ", account_id=" + account_id +
                ", archive=" + archive +
                '}';
    }
}
