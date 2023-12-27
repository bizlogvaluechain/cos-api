package com.bizlog.rms.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "client")
@Data
public class Client {

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

    @Column(name = "dateOfOnboarding")
    private Long dateOfOnboarding;

    @PrePersist
    @PreUpdate
    private void updateEpochTimes() {
        dateOfOnboarding = convertToEpochSeconds(LocalDateTime.ofEpochSecond(dateOfOnboarding, 0, ZoneOffset.UTC));
    }

    private long convertToEpochSeconds(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.UTC);
    }

}
