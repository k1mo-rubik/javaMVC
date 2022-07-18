package ru.k1morng.mvc_practice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AuthorDto {
    private UUID id;
    private String name;
    private int age;
}
