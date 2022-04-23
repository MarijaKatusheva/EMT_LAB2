package com.example.lab2_emt.service;

import com.example.lab2_emt.model.Author;
import com.example.lab2_emt.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(String name, String surname, Long countryId);
}
