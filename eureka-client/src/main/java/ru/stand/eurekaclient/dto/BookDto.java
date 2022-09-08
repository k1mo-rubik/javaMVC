package ru.stand.eurekaclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BookDto {
    private UUID id;
    private String name;
    private int pages;
}
