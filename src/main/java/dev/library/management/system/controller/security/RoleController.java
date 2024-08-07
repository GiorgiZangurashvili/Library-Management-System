package dev.library.management.system.controller.security;

import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.domain.enums.RoleName;
import dev.library.management.system.service.interfaces.security.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/security/roles")
@RequiredArgsConstructor
@Tag(name = "Roles API")
public class RoleController {
    private final RoleService roleService;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            description = "Assign role to user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully assigned a role to user"
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
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Role with specified roleName is not found"
                    )
            }
    )
    public ResponseEntity<UserWithRoleResponseDto> assignRoleToUser(
            @RequestParam String username,
            @RequestParam RoleName roleName
    ) {
        return ResponseEntity.ok(roleService.assignRoleToUser(username, roleName));
    }

}
