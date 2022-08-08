package ru.k1morng.mvc_practice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class AuthorDto {
    private UUID id;
    @NotNull(message = "name cannot be empty")
    @Size(min=1, max=32, message ="name cannot be empty or longer than 32 characters")
    private String name;
    @Min(value = 13, message = "age cannot be lower than 13")
    @Max(value = 110, message = "age cannot be higher than 110")
    private int age;

    Set<BookDto> bookSet;
}
