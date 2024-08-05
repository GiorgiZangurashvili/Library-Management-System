package dev.library.management.system.controller;

import dev.library.management.system.domain.dto.request.UserRequestDto;
import dev.library.management.system.domain.dto.response.UserResponseDto;
import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserWithRoleResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.saveUser(userRequestDto));
    }

    @PutMapping("/credentials")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    public ResponseEntity<UserResponseDto> updateUserCredentials(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUserCredentials(userRequestDto));
    }

    @DeleteMapping("/delete/id")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    public ResponseEntity<UserResponseDto> deleteUserById(@RequestParam long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @DeleteMapping("/delete/username")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    public ResponseEntity<UserResponseDto> deleteUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.deleteUserByUsername(username));
    }

}
