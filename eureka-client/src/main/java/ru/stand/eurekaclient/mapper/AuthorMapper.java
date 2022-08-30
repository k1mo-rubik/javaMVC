package ru.stand.eurekaclient.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.stand.eurekaclient.dto.AuthorDto;
import ru.stand.eurekaclient.dto.BookDto;
import ru.stand.eurekaclient.entity.Author;
import ru.stand.eurekaclient.entity.Book;

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
