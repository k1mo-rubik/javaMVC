package ru.stand.eurekaclient.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class AuthorDto {
    private UUID id;
    @NotBlank(message = "name cannot be empty or whiteSpace")
    @NotNull(message = "name cannot be null")
    @Size(min=1, max=32, message ="name cannot be empty or longer than 32 characters")
    private String name;
    @Min(value = 13, message = "age cannot be lower than 13")
    @Max(value = 110, message = "age cannot be higher than 110")
    private int age;

    Set<BookDto> bookSet;
}
