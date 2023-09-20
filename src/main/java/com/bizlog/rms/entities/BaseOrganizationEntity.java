package com.bizlog.rms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@MappedSuperclass
@Data
public class BaseOrganizationEntity {

    public static final String ORG_ID = "org_id";

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = ORG_ID, nullable = false, updatable = false)
    public Organization organization;

    @JsonProperty("organizationId")
    public Long getOrganizationId() {
        if (Objects.nonNull(organization)) {
            if (HibernateProxy.class.isInstance(organization)) {
                String s = ((HibernateProxy) organization).getHibernateLazyInitializer().getIdentifier().toString();
                return Long.getLong(s);
            }
            return organization.getId();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

}
