package com.bizlog.rms.entities.role;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Entity
@Data
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public static Role getRole(@NotBlank String role) {
        if (StringUtils.isNotEmpty(role)) {
            if (role.equals("super_admin")) {
                return new Role(1l, Roles.ROLE_SUPER_ADMIN.getRoleString());
            } else if (role.equals("admin")) {
                return new Role(2l, Roles.ROLE_ADMIN.getRoleString());
            }
        }

        return null;
    }

}
