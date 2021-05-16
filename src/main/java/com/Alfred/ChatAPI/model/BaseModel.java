package com.Alfred.ChatAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    public BaseModel() {}

    public BaseModel(Long id) { this.id = id; }

    @JsonProperty("id")
    public Long getID() { return id; }
}
