package dev.library.management.system.controller.security;

import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.domain.enums.RoleName;
import dev.library.management.system.service.interfaces.security.RoleService;
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
public class RoleController {
    private final RoleService roleService;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserWithRoleResponseDto> assignRoleToUser(
            @RequestParam String username,
            @RequestParam RoleName roleName
    ) {
        return ResponseEntity.ok(roleService.assignRoleToUser(username, roleName));
    }

}
