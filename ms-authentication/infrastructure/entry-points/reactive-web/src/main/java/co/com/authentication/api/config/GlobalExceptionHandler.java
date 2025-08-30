package co.com.authentication.api.config;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.com.authentication.model.user.exeptions.DocumentAlreadyExistsException;
import co.com.authentication.model.user.exeptions.ErrorCodes;
import co.com.authentication.model.user.exeptions.UnderAgeException;
import co.com.authentication.model.user.exeptions.UserAlreadyExistsException;
import co.com.authentication.usecase.registeruser.InvalidSalaryException;




@RestControllerAdvice
public class GlobalExceptionHandler {

     private ResponseEntity<ErrorResponse> buildError(ErrorCodes errorCode, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler(UnderAgeException.class)
    public ResponseEntity<ErrorResponse> handleUnderAge(UnderAgeException ex) {
        return buildError(ex.getErrorCode(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSalaryException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSalary(InvalidSalaryException ex) {
        return buildError(ex.getErrorCode(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return buildError(ex.getErrorCode(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DocumentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDocumentAlreadyExists(DocumentAlreadyExistsException ex) {
        return buildError(ex.getErrorCode(), HttpStatus.CONFLICT);
    }
}