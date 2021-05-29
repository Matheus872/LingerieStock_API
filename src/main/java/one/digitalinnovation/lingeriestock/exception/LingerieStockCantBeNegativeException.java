package one.digitalinnovation.lingeriestock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LingerieStockCantBeNegativeException extends Exception {

    public LingerieStockCantBeNegativeException(Long id, int quantityToIncrement, Integer actualQuantity) {
        super(String.format("Decrement informed %s its greater than the actual stock (%s) of lingerie with %s ID",quantityToIncrement ,actualQuantity,id ));
    }
}
