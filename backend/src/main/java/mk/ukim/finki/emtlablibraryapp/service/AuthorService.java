package mk.ukim.finki.emtlablibraryapp.service;

import mk.ukim.finki.emtlablibraryapp.model.Author;
import mk.ukim.finki.emtlablibraryapp.model.Country;
import mk.ukim.finki.emtlablibraryapp.model.dto.AuthorDto;
import mk.ukim.finki.emtlablibraryapp.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    // CRUD operations
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Long country);

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> edit(Long id, String name, String surname, Long countryId);

    Optional<Author> edit(Long id, AuthorDto authorDto);

    void deleteById(Long id);
}
