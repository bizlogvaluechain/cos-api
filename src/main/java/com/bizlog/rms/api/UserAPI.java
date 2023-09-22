package com.bizlog.rms.api;

import com.bizlog.rms.dto.OrganizationDTO;
import com.bizlog.rms.dto.users.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/{orgId}/users")
public interface UserAPI {


    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getById(Long orgId, Long id);

    @PostMapping
    ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO);

}
