package com.bizlog.rms.entities.clientinfo.contactinformation;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client_Finance_Contact_info_table")
public class FinanceContactInformation  extends BaseClientEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "emailId", nullable = false)
    private String emailId;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
}
