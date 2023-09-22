package com.bizlog.rms.entities.users;
import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@ToString
public class User extends BaseClientEntity {


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String mobile;


}
