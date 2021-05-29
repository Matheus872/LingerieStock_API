package one.digitalinnovation.lingeriestock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LingerieAlreadyRegisteredException extends Exception{

    public LingerieAlreadyRegisteredException(String LingerieName) {
        super(String.format("Lingerier with name %s already registered in the system.", LingerieName));
    }
}
