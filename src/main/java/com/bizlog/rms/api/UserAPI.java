package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.users.UserDTO;
import com.bizlog.rms.entities.role.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/cos/{clientId}")
public interface UserAPI {

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getById(Long clientId, Long id);

    @PostMapping
    ResponseEntity<UserDTO> create(UserDTO userDTO);

    @GetMapping
    ResponseEntity<PageResponse<UserDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> update(Long Id, Long id, UserDTO userDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @PostMapping("/roles")
    ResponseEntity<String> createRole(Role role);

    @GetMapping("/users")
    ResponseEntity<PageResponse<UserDTO>> findUserByRole(@PathVariable Long clientId, @RequestParam String role,
            Pageable pageable);

}
