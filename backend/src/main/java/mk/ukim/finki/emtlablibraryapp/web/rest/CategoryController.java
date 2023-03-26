package mk.ukim.finki.emtlablibraryapp.web.rest;


import mk.ukim.finki.emtlablibraryapp.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryController {
    @GetMapping
    public ResponseEntity<Category[]> getCategories() {
        return ResponseEntity.ok(Category.values());
    }
}
