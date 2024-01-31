package com.bizlog.rms.entities.sop.frequency;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "holiday_applicable_table")
public class HolidayApplicable {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "publicHolidays", nullable = false)
    private Boolean publicHolidays;
    @Column(name = "bizlogHolidays", nullable = false)
    private Boolean bizlogHolidays;
    @Column(name = "clientHolidaays", nullable = false)
    private Boolean clientHolidaays;
}
