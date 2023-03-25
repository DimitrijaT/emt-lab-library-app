package mk.ukim.finki.emtlablibraryapp.model.exceptions;

public class InvalidAuthorException extends RuntimeException {
    public InvalidAuthorException(Long id) {
        System.out.println(String.format("Author with id: %d does not exist", id));
    }
}
