package com.example.lab2_emt.service.impl;

import com.example.lab2_emt.model.Author;
import com.example.lab2_emt.model.Book;
import com.example.lab2_emt.model.enumeration.Category;
import com.example.lab2_emt.model.exception.AuthorNotFoundException;
import com.example.lab2_emt.model.exception.BookNotFoundException;
import com.example.lab2_emt.model.exception.InvalidArgumentException;
import com.example.lab2_emt.repository.jpa.AuthorRepository;
import com.example.lab2_emt.repository.jpa.BookRepository;
import com.example.lab2_emt.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Transactional
    @Override
    public Optional<Book> save(String name, String category, Long authorId, Long availableCopies) {
        if (name == "" || category == null || authorId == null || availableCopies == null) {
            throw new InvalidArgumentException();
        }
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        Category categoryToAdd;
        switch (category){
            case "THRILLER": categoryToAdd = Category.THRILER;
            case "DRAMA": categoryToAdd = Category.DRAMA;
            case "NOVEL": categoryToAdd = Category.NOVEL;
            default: categoryToAdd = Category.BIOGRAPHY;
        }
        Book book = new Book(name,categoryToAdd, author, availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        this.bookRepository.delete(book);
    }

    @Override
    public Optional<Book> edit(String name, Category category, Long authorId, Long availableCopies) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = this.bookRepository.findByNameAndAuthor(name,author);
        book.setAuthor(author);
        book.setCategory(category);
        book.setName(name);
        book.setAvailableCopies(availableCopies);

        return Optional.of(this.bookRepository.save(book));
    }
}
