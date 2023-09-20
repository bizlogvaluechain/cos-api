package com.bizlog.rms.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "organization")
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(length = 80)
    private String domainName;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "type")
    private String type;

}
