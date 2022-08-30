package ru.stand.eurekaclient.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.stand.eurekaclient.dto.BookDto;
import ru.stand.eurekaclient.entity.Book;

import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class})
public abstract class BookMapper {

    public static final BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    public abstract Book fromDto(BookDto bookDto);

    @InheritInverseConfiguration
    public abstract BookDto toBookDto(Book book);

}
