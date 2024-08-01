package dev.library.management.system.domain.mapper;

import dev.library.management.system.domain.dto.response.CategoryResponseDto;
import dev.library.management.system.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDto mapCategoryToCategoryResponseDto(Category category);

}
