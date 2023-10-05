package com.bizlog.rms.repository;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.role.Role;
import com.bizlog.rms.entities.users.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface UserRepository extends BaseClientRepository<User, Long> {
    Page<User> findByClientAndRolesIn(Client client, Set<Role> roles, Pageable pageable);
}
