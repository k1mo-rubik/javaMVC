package ru.stand.eurekaclient.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmptyPageException extends Exception{
    private String message;
}
