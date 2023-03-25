package mk.ukim.finki.emtlablibraryapp.repository;

import mk.ukim.finki.emtlablibraryapp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
