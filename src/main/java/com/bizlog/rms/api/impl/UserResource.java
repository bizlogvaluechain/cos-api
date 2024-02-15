package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.UserAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.users.UserDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.role.Role;
import com.bizlog.rms.entities.users.User;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.RoleRepository;
import com.bizlog.rms.repository.UserRepository;
import com.bizlog.rms.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController

public class UserResource extends BaseClientResource<User, UserDTO, UserDTO> implements UserAPI {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserResource(BaseClientRepository<User, Long> baseClientRepository, UserService userService,
            UserRepository userRepository, RoleRepository roleRepository) {
        super(baseClientRepository);
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<UserDTO> getById(Long clientId, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", "id", id));
        UserDTO userDTO = getMapper().toDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @Override
    public ResponseEntity<PageResponse<UserDTO>> getAll(@PathVariable Long clientId, Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        Organization organization = getOrganizationRepository().findById(userDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("  Client not found", "id", userDTO.getClientId()));
        User user = getMapper().toEntity(userDTO);
        user.setOrganization(organization);
        user = userRepository.save(user);
        UserDTO userDTO1 = getMapper().toDTO(user);
        return ResponseEntity.ok().body(userDTO1);
    }

    @Transactional
    @Override
    public ResponseEntity<UserDTO> update(@PathVariable Long clientId, @PathVariable("id") Long id,
            @RequestBody @Valid UserDTO userDTO) {
        return super.update(clientId, id, userDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long clientId, @PathVariable("id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", "id", id));

        if (!user.getOrganization().getId().equals(clientId)) {
            throw new ResourceNotFoundException("User not found for client", "id", id);
        }
        userRepository.delete(user);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PageResponse<UserDTO>> findUserByRole(@PathVariable Long clientId, @RequestParam String role,
            Pageable pageable) {
        Page<User> list = userService.findAllByRole(clientId, role, pageable);
        List<UserDTO> outPutDTO = list.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        Map<String, Object> meta = getMetaData(list);
        PageResponse<UserDTO> pageResponse = new PageResponse<>(meta, outPutDTO);
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createRole(@RequestBody Role role) {
        Role savedRole = roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role created with ID: " + savedRole.getId());
    }

    @Override
    protected User toEntity(UserDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected UserDTO toDTO(User entity) {
        return getMapper().toDTO(entity);
    }
}
