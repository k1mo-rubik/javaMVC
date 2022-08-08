package ru.k1morng.mvc_practice.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.k1morng.mvc_practice.dto.AuthorDto;
import ru.k1morng.mvc_practice.dto.BookDto;
import ru.k1morng.mvc_practice.entity.Author;
import ru.k1morng.mvc_practice.entity.Book;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class},
        uses = {BookMapper.class, Set.class})
public abstract class AuthorMapper {
    public static final AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "deleted", expression = "java(false)")
    public abstract Author fromDto(AuthorDto authorDto);

    @InheritInverseConfiguration
    @Mapping(target = "bookSet", source = "books", qualifiedByName = "bookDtoList")
    public abstract AuthorDto toAuthorDto(Author author);

    @Named("bookDtoList")
    public static Set<BookDto> bookDtoList(Set<Book> books) {
        return books.stream().map(BookMapper.INSTANCE::toBookDto).collect(Collectors.toSet());//метод референс
    }

}
