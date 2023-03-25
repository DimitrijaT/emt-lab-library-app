package mk.ukim.finki.emtlablibraryapp.repository;

import mk.ukim.finki.emtlablibraryapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByNameIgnoreCase(String name);

}
