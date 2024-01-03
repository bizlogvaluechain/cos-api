package com.bizlog.rms.entities.clientinfo;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity

@Table(name = "client_Contact_info_table")
public class ContactInformation extends BaseClientEntity {
    @Column(name = "contactPersonName", nullable = false)
    private String contactPersonName;
    @Column(name = "contactPersonTitle", nullable = false)
    private String contactPersonTitle;
    @Column(name = "contactPersonsEmailId", nullable = false)
    private String contactPersonsEmailId;
    @Column(name = "contactPersonsPhoneNumber", nullable = false)
    private String contactPersonsPhoneNumber;
}
