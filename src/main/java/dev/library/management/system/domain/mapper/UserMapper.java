package dev.library.management.system.domain.mapper;

import dev.library.management.system.domain.dto.request.UserRequestDto;
import dev.library.management.system.domain.dto.response.UserResponseDto;
import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.domain.entity.User;
import dev.library.management.system.domain.entity.security.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapUserRequestDtoToUser(UserRequestDto userRequestDto);
    UserResponseDto mapUserToUserResponseDto(User user);

//    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRolesToNames")
    UserWithRoleResponseDto mapUserToUserWithRoleResponseDto(User user);

//    default List<String> mapRolesToNames(List<Role> roles) {
//        if (roles == null) return null;
//
//        return roles.stream().map(r -> r.getName().name()).toList();
//    }
}
