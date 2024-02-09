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
    @Column(name = "searchAndTrack", nullable = false)
    private String searchAndTrack;
    @Column(name = "clientReports", nullable = false)
    private Boolean clientReports;
    @Column(name = "LiveTicketScan", nullable = false)
    private Boolean liveTicketScan;

}
