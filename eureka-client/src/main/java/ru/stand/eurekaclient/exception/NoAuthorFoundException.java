package ru.stand.eurekaclient.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.NoSuchElementException;


//TODO настроить работу исключения по логике
@Data
@AllArgsConstructor
public class NoAuthorFoundException extends NoSuchElementException {
    public String message;
}
