package pl.bpiotrowski.tictactoe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(Long id) {
        super("Field with id: " + id + " not found");
    }
}
