package mk.ukim.finki.emtlablibraryapp.model.exceptions;

public class InvalidCountryException extends RuntimeException {
    public InvalidCountryException(Long id) {
        System.out.println(String.format("Country with id: %d does not exist", id));
    }
}
