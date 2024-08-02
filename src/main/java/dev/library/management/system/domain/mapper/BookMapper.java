package dev.library.management.system.domain.mapper;

import dev.library.management.system.domain.dto.request.BookRequestDto;
import dev.library.management.system.domain.dto.response.BookResponseDto;
import dev.library.management.system.domain.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                AuthorMapper.class,
                CategoryMapper.class
        }
)
public interface BookMapper {

    @Mappings(value = {
            @Mapping(source = "author", target = "author"),
            @Mapping(source = "categories", target = "categories")
    })
    BookResponseDto mapBookToBookResponseDto(Book book);

    Book mapBookRequestDtoToBook(BookRequestDto bookRequestDto);

}
