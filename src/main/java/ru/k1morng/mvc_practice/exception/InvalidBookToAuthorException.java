package ru.k1morng.mvc_practice.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidBookToAuthorException extends Exception{
    public String message;
}
