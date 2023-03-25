package mk.ukim.finki.emtlablibraryapp.model.exceptions;

public class InvalidBookException extends RuntimeException {
    public InvalidBookException(Long id) {
        System.out.println(String.format("Book with id: %d does not exist", id));
    }
}
