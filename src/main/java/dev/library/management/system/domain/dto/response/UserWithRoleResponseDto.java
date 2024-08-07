package dev.library.management.system.domain.dto.response;

import java.io.Serializable;
import java.util.List;

public record UserWithRoleResponseDto(
        long id,
        String username
        // TODO UPDATE MAPPER
//        List<String> roles
) implements Serializable {}
