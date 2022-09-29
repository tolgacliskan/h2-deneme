package com.example.h2deneme.model;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ProductIDModel {

    @Id
    private Long id;

    public ProductIDModel(Long id) {
        this.id = id;
    }

    public ProductIDModel() {
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
}
