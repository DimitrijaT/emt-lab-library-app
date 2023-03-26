package mk.ukim.finki.emtlablibraryapp.model.dto;

import lombok.Data;
import mk.ukim.finki.emtlablibraryapp.model.Category;

@Data
public class BookDto {
    private String name;
    private Category category;
    private Long authorId;
    private int availableCopies;
}
