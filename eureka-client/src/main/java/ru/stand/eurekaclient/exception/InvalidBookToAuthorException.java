package ru.stand.eurekaclient.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidBookToAuthorException extends Exception{
    public String message;
}
