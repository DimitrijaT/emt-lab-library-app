package mk.ukim.finki.emtlablibraryapp.service.impl;

import mk.ukim.finki.emtlablibraryapp.model.Author;
import mk.ukim.finki.emtlablibraryapp.model.Book;
import mk.ukim.finki.emtlablibraryapp.model.Category;
import mk.ukim.finki.emtlablibraryapp.model.dto.BookDto;
import mk.ukim.finki.emtlablibraryapp.model.exceptions.InvalidAuthorException;
import mk.ukim.finki.emtlablibraryapp.model.exceptions.InvalidBookException;
import mk.ukim.finki.emtlablibraryapp.repository.AuthorRepository;
import mk.ukim.finki.emtlablibraryapp.repository.BookRepository;
import mk.ukim.finki.emtlablibraryapp.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    final AuthorRepository authorRepository;
    final BookRepository bookRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, int availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() ->
                new InvalidAuthorException(authorId)
        );
        Book book = new Book(name, category, author, availableCopies);
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() ->
                new InvalidAuthorException(bookDto.getAuthorId())
        );
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, int availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() ->
                new InvalidBookException(id)
        );
        Author author = this.authorRepository.findById(authorId).orElseThrow(() ->
                new InvalidAuthorException(authorId)
        );
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }


    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() ->
                new InvalidBookException(id)
        );
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() ->
                new InvalidAuthorException(bookDto.getAuthorId())
        );
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(book.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() ->
                new InvalidBookException(id)
        );
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        this.bookRepository.save(book);
        return Optional.of(book);
    }
}
