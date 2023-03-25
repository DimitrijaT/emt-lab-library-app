package mk.ukim.finki.emtlablibraryapp.web.rest;


import mk.ukim.finki.emtlablibraryapp.model.Author;
import mk.ukim.finki.emtlablibraryapp.model.dto.AuthorDto;
import mk.ukim.finki.emtlablibraryapp.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorRestController {

    final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    private List<Author> findAll() {
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Author> findById(@PathVariable Long id) {
        return this.authorService.findById(id).map(
                        author -> ResponseEntity.ok().body(author)
                )
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    private ResponseEntity<Author> save(@RequestBody AuthorDto authorDto) {
        return this.authorService.save(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    private ResponseEntity<Author> save(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return this.authorService.edit(id, authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.authorService.deleteById(id);
        if (this.authorService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
