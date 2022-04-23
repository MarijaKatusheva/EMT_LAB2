package com.example.lab2_emt.service;

import com.example.lab2_emt.model.Author;
import com.example.lab2_emt.model.Book;
import com.example.lab2_emt.model.enumeration.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, String category, Long authorId, Long availableCopies);

    void delete(Long id);

    Optional<Book> edit(String name, Category category, Long authorId, Long availableCopies);


}
