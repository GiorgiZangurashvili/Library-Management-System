package dev.library.management.system.domain.mapper;

import dev.library.management.system.domain.dto.response.BorrowingHistoryResponseDto;
import dev.library.management.system.domain.entity.BorrowingHistory;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BorrowingHistoryMapper {

    @Mappings(value = {
            @Mapping(source = "borrowingUser.id", target = "borrowerId"),
            @Mapping(source = "borrowedBook.id", target = "borrowedBookId")
    })
    BorrowingHistoryResponseDto mapBorrowingHistoryToBorrowingHistoryResponseDto(BorrowingHistory borrowingHistory);
    
}
