package ru.k1morng.mvc_practice.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ControllerExceptionHandler extends Exception {
    public ControllerExceptionHandler(String message) {
        super(message);
    }
}
