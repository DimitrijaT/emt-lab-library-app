package mk.ukim.finki.emtlablibraryapp.service.impl;


import mk.ukim.finki.emtlablibraryapp.model.Author;
import mk.ukim.finki.emtlablibraryapp.model.Country;
import mk.ukim.finki.emtlablibraryapp.model.dto.AuthorDto;
import mk.ukim.finki.emtlablibraryapp.model.exceptions.InvalidAuthorException;
import mk.ukim.finki.emtlablibraryapp.model.exceptions.InvalidCountryException;
import mk.ukim.finki.emtlablibraryapp.repository.AuthorRepository;
import mk.ukim.finki.emtlablibraryapp.repository.CountryRepository;
import mk.ukim.finki.emtlablibraryapp.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    final AuthorRepository authorRepository;
    final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new InvalidCountryException(countryId));
        Author author = new Author(name, surname, country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountryId()).orElseThrow(() -> new InvalidCountryException(authorDto.getCountryId()));
        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new InvalidCountryException(countryId));
        Author author = this.authorRepository.findById(id).orElseThrow(() ->
                new InvalidAuthorException(id)
        );
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountryId()).orElseThrow(() -> new InvalidCountryException(authorDto.getCountryId()));
        Author author = this.authorRepository.findById(id).orElseThrow(() ->
                new InvalidAuthorException(id)
        );
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
