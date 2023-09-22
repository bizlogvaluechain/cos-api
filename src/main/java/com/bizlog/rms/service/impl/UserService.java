package com.bizlog.rms.service.impl;

import com.bizlog.rms.dto.users.UserDTO;
import com.bizlog.rms.repository.OrganizationRepository;
import com.bizlog.rms.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public UserDTO getUserById(Long orgId, Long id){
        return null;
    }
}
