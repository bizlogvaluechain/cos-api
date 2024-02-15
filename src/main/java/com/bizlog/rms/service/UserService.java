package com.bizlog.rms.service;

import com.bizlog.rms.dto.users.UserDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.role.Role;
import com.bizlog.rms.entities.users.User;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.OrganizationRepository;
import com.bizlog.rms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public UserDTO getUserById(Long clientId, Long id) {
        return null;
    }

    public Page<User> findAllByRole(Long clientId, String role, Pageable pageable) {
        log.info("------------->>>>>>>>>>>>>>>>>" + role);
        Organization organization = organizationRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(" client not found", "id", clientId));
        final Role dbRole = Role.getRole(role);
        Page<User> users = userRepository.findByOrganizationAndRolesIn(organization, Set.of(dbRole), pageable);
        return users;
    }

}
