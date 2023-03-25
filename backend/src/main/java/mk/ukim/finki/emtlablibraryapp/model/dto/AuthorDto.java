package mk.ukim.finki.emtlablibraryapp.model.dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import mk.ukim.finki.emtlablibraryapp.model.Country;

@Data
public class AuthorDto {

    private String name;

    private String surname;

    private Long countryId;
}
