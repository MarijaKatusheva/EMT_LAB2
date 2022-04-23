package com.example.lab2_emt.repository.jpa;

import com.example.lab2_emt.model.Author;
import com.example.lab2_emt.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    //void deleteByNameAndAuthor(String name, Author author);
    Book findByNameAndAuthor(String name,Author author);
}
