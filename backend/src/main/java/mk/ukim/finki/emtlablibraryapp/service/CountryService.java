package mk.ukim.finki.emtlablibraryapp.service;

import mk.ukim.finki.emtlablibraryapp.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    // Basic CRUD operations

    Optional<Country> save(String name, String continent);

    Optional<Country> edit(Long id, String name, String continent);

    Optional<Country> findById(Long id);

    void deleteById(Long id);

    List<Country> findAll();
}
