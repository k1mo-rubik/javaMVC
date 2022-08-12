package ru.k1morng.mvc_practice.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.k1morng.mvc_practice.exception.EmptyPageException;
import ru.k1morng.mvc_practice.exception.AuthorIsDeletedException;
import ru.k1morng.mvc_practice.exception.InvalidBookToAuthorException;
import ru.k1morng.mvc_practice.exception.NoAuthorFoundException;

import java.util.HashMap;
import java.util.Map;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
@RestControllerAdvice
public class AuthorControllerExceptionHandler extends Exception {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(AuthorIsDeletedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected Map<String, String> handleAuthorNotFoundException(AuthorIsDeletedException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    //    @ExceptionHandler(EmptyPageException.class)
//    protected ResponseEntity<EmptyPageException> handleEmptyPageException(){
//        return new ResponseEntity<>(new EmptyPageException("This page is empty"), HttpStatus.FORBIDDEN);
//    }

    //TODO все HTTP-303 должны автоматически переходить на ближайшую страницу
    @ExceptionHandler(EmptyPageException.class)
    @ResponseStatus(HttpStatus.SEE_OTHER)
    protected Map<String, String> handleEmptyPageException(EmptyPageException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return errorMap;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    protected Map<String, String> handleInvalidBookToAuthorException(InvalidBookToAuthorException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return errorMap;
    }

    //TODO все HTTP-303 должны переводить на ближайшего автора (если введено имя),
    // по UUID выдавать JSON с предложением ближайшего верного идентификатора
    @ExceptionHandler
    @ResponseStatus(HttpStatus.SEE_OTHER)
    protected Map<String, String> handleNoAuthorFoundException(NoAuthorFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return errorMap;
    }


}
