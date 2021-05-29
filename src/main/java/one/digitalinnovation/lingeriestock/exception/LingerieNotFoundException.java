package one.digitalinnovation.lingeriestock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LingerieNotFoundException extends Exception {

    public LingerieNotFoundException(String lingerieName) {
        super(String.format("Lingerie with name %s not found in the system.", lingerieName));
    }

    public LingerieNotFoundException(Long id) {
        super(String.format("Lingerie with id %s not found in the system.", id));
    }
}
