package kg.abr.backendShopBonus.shopBonus.exceptions;

import kg.abr.backendShopBonus.shopBonus.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {UserExistException.class})
    public ResponseEntity<?> userExistException(UserExistException exception) {
        MessageResponse messageResponse = new MessageResponse(exception.getMessage());
        return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
    }
}
