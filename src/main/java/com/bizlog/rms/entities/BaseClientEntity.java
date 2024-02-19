package com.bizlog.rms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseClientEntity {

    public static final String CLIENT_ID = "client_id";

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = CLIENT_ID, nullable = false, updatable = false)
    public Organization organization;

    @JsonProperty("clientId")
    public Long getclientId() {
        if (Objects.nonNull(organization)) {
            if (HibernateProxy.class.isInstance(organization)) {
                String s = ((HibernateProxy) organization).getHibernateLazyInitializer().getIdentifier().toString();
                return Long.getLong(s);
            }
            return organization.getId();
        }
        return null;
    }

}
