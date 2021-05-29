package one.digitalinnovation.lingeriestock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LingerieStockExceededException extends Exception {

    public LingerieStockExceededException(Long id, int quantityToIncrement) {
        super(String.format("Lingerie with %s ID to increment informed exceeds the max stock capacity: %s", id, quantityToIncrement));
    }
}
