package dev.library.management.system.domain.mapper;

import dev.library.management.system.domain.dto.response.BorrowingHistoryResponseDto;
import dev.library.management.system.domain.entity.BorrowingHistory;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BorrowingHistoryMapper {

    BorrowingHistoryResponseDto mapBorrowingHistoryToBorrowingHistoryResponseDto(BorrowingHistory borrowingHistory);

    @AfterMapping
    default void afterMappingBorrowingHistoryToResponseDto(
            BorrowingHistory borrowingHistory,
            @MappingTarget BorrowingHistoryResponseDto responseDto
    ) {
        responseDto.setBorrowerId(borrowingHistory.getBorrowingUser().getId());
        responseDto.setBorrowedBookId(borrowingHistory.getBorrowedBook().getId());
    }
}
