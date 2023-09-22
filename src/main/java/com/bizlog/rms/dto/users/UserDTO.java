package com.bizlog.rms.dto.users;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String mobile;
    private String role;
}
