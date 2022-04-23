package com.example.lab2_emt.model;

import com.example.lab2_emt.model.enumeration.Category;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;
    @ManyToOne
    private Author author;
    private Long availableCopies;

    public Book(String name, Category category, Author author, Long availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book() {

    }
}
