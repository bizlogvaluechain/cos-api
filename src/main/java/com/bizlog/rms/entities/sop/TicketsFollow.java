package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ticket_follow_up_tbl")
@Data
public class TicketsFollow extends BaseClientEntity {
    @Column(name = "searchTrack", nullable = false)
    private String searchTrack;
    @Column(name = "clientReport", nullable = false)
    private String clientReport;
    @Column(name = "LiveTicketScan", nullable = false)
    private String LiveTicketScan;

}
