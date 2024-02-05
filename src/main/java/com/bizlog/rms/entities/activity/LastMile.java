package com.bizlog.rms.entities.activity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "activity_last_Mile_tbl")
public class LastMile extends Mile {

}
