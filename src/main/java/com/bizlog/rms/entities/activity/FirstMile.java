package com.bizlog.rms.entities.activity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.envers.Audited;

@Data
@Entity
@Audited
@Table(name = "activity_first_Mile_tbl")
public class FirstMile extends Mile {

}
