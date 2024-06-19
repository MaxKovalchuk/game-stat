package com.andersen.gamestat.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper=false)
public class GameStatException extends RuntimeException {

    private final HttpStatus httpStatusCode;

    public GameStatException(
            String message,
            HttpStatus httpStatusCode
    ) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }


}
