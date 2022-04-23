package com.example.lab2_emt.service.impl;

import com.example.lab2_emt.model.Author;
import com.example.lab2_emt.model.Country;
import com.example.lab2_emt.model.exception.AuthorNotFoundException;
import com.example.lab2_emt.model.exception.CountryNotFoundException;
import com.example.lab2_emt.model.exception.InvalidArgumentException;
import com.example.lab2_emt.repository.jpa.AuthorRepository;
import com.example.lab2_emt.repository.jpa.CountryRepository;
import com.example.lab2_emt.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return  this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(this.authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException(id)));
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        if(name=="" || surname=="" || countryId==null){
            throw  new InvalidArgumentException();
        }
        Country country=countryRepository.findById(countryId).orElseThrow(()-> new CountryNotFoundException(countryId));
        Author author=new Author(name,surname,country);
        return Optional.of(this.authorRepository.save(author));
    }
}
