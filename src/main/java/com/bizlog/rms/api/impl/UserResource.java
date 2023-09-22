package com.bizlog.rms.api.impl;


import com.bizlog.rms.api.UserAPI;
import com.bizlog.rms.dto.users.UserDTO;
import com.bizlog.rms.entities.users.User;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.UserRepository;
import com.bizlog.rms.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
public class UserResource extends BaseClientResource<User, UserDTO, UserDTO> implements UserAPI {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserResource(BaseClientRepository baseClientRepository, UserService userService,
                        UserRepository userRepository) {
        super(baseClientRepository);
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDTO> getById(Long orgId, Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found", "id", id));
            UserDTO userDTO = getMapper().toDTO(user);
            return ResponseEntity.ok().body(userDTO);

    }

    @Override
    public ResponseEntity<UserDTO> create(@Valid UserDTO userDTO) {
        User user = getMapper().toEntity(userDTO);
        user = userRepository.save(user);
        UserDTO userDTO1 = getMapper().toDTO(user);
        return ResponseEntity.ok().body(userDTO1);
    }

}
