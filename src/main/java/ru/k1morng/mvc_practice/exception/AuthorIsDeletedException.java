package ru.k1morng.mvc_practice.exception;

public class AuthorIsDeletedException extends Exception{
    public AuthorIsDeletedException(String message) {
        super(message);
    }
}
