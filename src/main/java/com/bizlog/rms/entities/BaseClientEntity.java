package com.bizlog.rms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@MappedSuperclass
@Data
public class BaseClientEntity {

    public static final String CLIENT_ID = "client_id";

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = CLIENT_ID, nullable = false, updatable = false)
    public Client client;

    @JsonProperty("clientId")
    public Long getclientId() {
        if (Objects.nonNull(client)) {
            if (HibernateProxy.class.isInstance(client)) {
                String s = ((HibernateProxy) client).getHibernateLazyInitializer().getIdentifier().toString();
                return Long.getLong(s);
            }
            return client.getId();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

}
