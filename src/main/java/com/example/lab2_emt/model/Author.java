package com.example.lab2_emt.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;
private String surname;
@OneToOne
private Country country;

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
