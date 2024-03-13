package com.bizlog.rms.auditlogs;

import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Data
@RevisionEntity(MyRevisionListener.class)
@Entity
public class MyRevisionEntity extends DefaultRevisionEntity {
    private String userName;
}
