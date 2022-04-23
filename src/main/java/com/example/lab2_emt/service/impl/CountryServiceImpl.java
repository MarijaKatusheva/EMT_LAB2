package com.example.lab2_emt.service.impl;


import com.example.lab2_emt.model.Country;
import com.example.lab2_emt.model.exception.CountryNotFoundException;
import com.example.lab2_emt.repository.jpa.CountryRepository;
import com.example.lab2_emt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findCountryById(Long id) {
        return Optional.of(this.countryRepository.findById(id)
                .orElseThrow(()-> new CountryNotFoundException(id)));
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        Country country=new Country(name,continent);
        return Optional.of(this.countryRepository.save(country));
    }
}
