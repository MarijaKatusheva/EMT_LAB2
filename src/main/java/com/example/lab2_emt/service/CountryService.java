package com.example.lab2_emt.service;

import com.example.lab2_emt.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findCountryById(Long id);
    Optional<Country>save(String name,String continent);
}
