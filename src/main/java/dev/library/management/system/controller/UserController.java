package dev.library.management.system.controller;

import dev.library.management.system.domain.dto.request.UserRequestDto;
import dev.library.management.system.domain.dto.response.UserResponseDto;
import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.service.aop.annotation.Loggable;
import dev.library.management.system.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
@Tag(name = "Users API")
@Loggable(className = "UserController")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    @Operation(
            description = "Create a new user",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created a new user"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    )
            }
    )
    public ResponseEntity<UserWithRoleResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.saveUser(userRequestDto));
    }

    @PutMapping("/credentials")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    @Operation(
            description = "Update existing user's credentials",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated user's credentials"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You have no authority to do this action"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User with username not found"
                    )
            }
    )
    public ResponseEntity<UserResponseDto> updateUserCredentials(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUserCredentials(userRequestDto));
    }

    @DeleteMapping("/disable/id")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    @Operation(
            description = "Disable a user by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted a user by id"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You have no authority to do this action"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User with specified id is not found"
                    )
            }
    )
    public ResponseEntity<UserResponseDto> disableUserById(@RequestParam long id) {
        return ResponseEntity.ok(userService.disableUserById(id));
    }

    @DeleteMapping("/disable/username")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    @Operation(
            description = "Disable a user by username",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted a user by username"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You have no authority to do this action"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User with specified username is not found"
                    )
            }
    )
    public ResponseEntity<UserResponseDto> disableUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.disableUserByUsername(username));
    }

}
