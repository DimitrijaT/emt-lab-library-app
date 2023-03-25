package mk.ukim.finki.emtlablibraryapp.service;

import mk.ukim.finki.emtlablibraryapp.model.Author;
import mk.ukim.finki.emtlablibraryapp.model.Book;
import mk.ukim.finki.emtlablibraryapp.model.Category;
import mk.ukim.finki.emtlablibraryapp.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Page<Book> findAllWithPagination(Pageable pageable);

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    //TODO: BookDTO save and edit

    Optional<Book> save(String name, Category category, Long authorId, int availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, Category category, Long authorId, int availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    Optional<Book> markAsTaken(Long id);
}
