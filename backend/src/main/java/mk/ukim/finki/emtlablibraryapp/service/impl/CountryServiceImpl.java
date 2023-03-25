package mk.ukim.finki.emtlablibraryapp.service.impl;

import mk.ukim.finki.emtlablibraryapp.model.Country;
import mk.ukim.finki.emtlablibraryapp.model.exceptions.InvalidCountryException;
import mk.ukim.finki.emtlablibraryapp.repository.CountryRepository;
import mk.ukim.finki.emtlablibraryapp.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        Country country = new Country(name, continent);
        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = this.countryRepository.findById(id).orElseThrow(() ->
                new InvalidCountryException(id));
        country.setName(name);
        country.setContinent(continent);
        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }
}
