package mk.ukim.finki.emtlablibraryapp.repository;

import mk.ukim.finki.emtlablibraryapp.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
