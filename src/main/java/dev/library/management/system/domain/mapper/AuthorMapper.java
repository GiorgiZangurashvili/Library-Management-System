package dev.library.management.system.domain.mapper;

import dev.library.management.system.domain.dto.request.AuthorRequestDto;
import dev.library.management.system.domain.dto.response.AuthorResponseDto;
import dev.library.management.system.domain.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponseDto mapAuthorToAuthorResponseDto(Author author);
    Author mapAuthorRequestDtoToAuthor(AuthorRequestDto authorRequestDto);

}
