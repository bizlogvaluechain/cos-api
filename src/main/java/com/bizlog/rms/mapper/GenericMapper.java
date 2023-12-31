package com.bizlog.rms.mapper;

import com.bizlog.rms.dto.ClientDTO;
import com.bizlog.rms.dto.ClientSettingDTO;
import com.bizlog.rms.dto.SOP_TAT.*;
import com.bizlog.rms.dto.clientengagement.ClientEngagementDTO;
import com.bizlog.rms.dto.clientinfo.*;
import com.bizlog.rms.dto.escalationMatrix.*;
import com.bizlog.rms.dto.frequency.FrequencyDTO;
import com.bizlog.rms.dto.locationService.LocationDTO;
import com.bizlog.rms.dto.notification.NotificationDTO;
import com.bizlog.rms.dto.productInformation.ProductInformationDTO;
import com.bizlog.rms.dto.users.UserDTO;
import com.bizlog.rms.entities.ClientSetting;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.clientengagement.ClientEngagement;
import com.bizlog.rms.entities.clientinfo.*;
import com.bizlog.rms.entities.sop.*;
import com.bizlog.rms.entities.escalationMatrix.*;
import com.bizlog.rms.entities.sop.frequency.Frequency;
import com.bizlog.rms.entities.location.Location;
import com.bizlog.rms.entities.sop.labourtoolvechile.Labour;
import com.bizlog.rms.entities.sop.labourtoolvechile.Vehicle;
import com.bizlog.rms.entities.sop.linehaul.Linehaul;
import com.bizlog.rms.entities.sop.notification.Notification;
import com.bizlog.rms.entities.productInformation.ProductInformation;
import com.bizlog.rms.entities.sop.packing.Packing;
import com.bizlog.rms.entities.sop.ticketInFlow.TicketCreationConfig;
import com.bizlog.rms.entities.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface GenericMapper {

    ClientDTO toDTO(Client entity);

    Client toEntity(ClientDTO dto);

    ClientSettingDTO toDTO(ClientSetting entity);

    ClientSetting toEntity(ClientSettingDTO dto);

    CustomerInfoDTO toDTO(CustomerInfo entity);

    CustomerInfo toEntity(CustomerInfoDTO dto);

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

    SOPActivityDTO toDTO(SOPActivity entity);

    SOPActivity toEntity(SOPActivityDTO dto);

    TATActivityDTO toDTO(TATActivity entity);

    TATActivity toEntity(TATActivityDTO dto);

    TicketCreationConfigDTO toDTO(TicketCreationConfig ticketCreationConfig);

    TicketCreationConfig toEntity(TicketCreationConfigDTO ticketCreationConfigDTO);

    Frequency toEntity(FrequencyDTO frequencyDTO);

    FrequencyDTO toDTO(Frequency frequency);

    ProductInformation toEntity(ProductInformationDTO productInformationDTO);

    ProductInformationDTO toDTO(ProductInformation productInformation);

    Notification toEntity(NotificationDTO notificationDTO);

    NotificationDTO toDTO(Notification notification);

    EscalationMatrix toEntity(EscalationMatrixDTO escalationMatrixDTO);

    EscalationMatrixDTO toDTO(EscalationMatrix escalationMatrix);

    Location toEntity(LocationDTO locationDTO);

    LocationDTO toDTO(Location location);

    // @Mapping(target = "ticketsVolume", source = "ticketsVolume")
    @Mapping(expression = "java(row.get(\"ticketsVolume\"))", target = "ticketsVolume")
    FrequencyDTO toDTO(Map<String, String> row);

    BillingInfo toEntity(BillingInfoDTO dto);

    BillingInfoDTO toDTO(BillingInfo entity);

    CompanyContactDetails toEntity(CompanyContactDetailsDTO dto);

    CompanyContactDetailsDTO toDTO(CompanyContactDetails entity);

    ClientStaging toEntity(ClientStagingDTO dto);

    ClientStagingDTO toDTO(ClientStaging entity);

    ClientCode toEntity(ClientCodeDTO dto);

    ClientCodeDTO toDTO(ClientCode entity);

    ShipmentInfo toEntity(ShipmentInfoDTO dto);

    ShipmentInfoDTO toDTO(ShipmentInfo entity);

    ClientEngagement toEntity(ClientEngagementDTO dto);

    ClientEngagementDTO toDTO(ClientEngagement entity);

    ClientHierarchy toEntity(ClientHierarchyDTO dto);

    ClientHierarchyDTO toDTO(ClientHierarchy entity);

    ContactInformation toEntity(ContactInformationDTO dto);

    ContactInformationDTO toDTO(ContactInformation entity);

    BizlogFinanceEscalation toEntity(BizlogFinanceEscalationDTO dto);

    BizlogFinanceEscalationDTO toDTO(BizlogFinanceEscalation entity);

    BizlogOpsEscalation toEntity(BizlogOpsEscalationDTO dto);

    BizlogOpsEscalationDTO toDTO(BizlogOpsEscalation entity);

    BizlogTechEscalation toEntity(BizlogTechEscalationDTO dto);

    BizlogTechEscalationDTO toDTO(BizlogTechEscalation entity);

    ClientFinanceEscalation toEntity(ClientFinanceEscalationDTO dto);

    ClientFinanceEscalationDTO toDTO(ClientFinanceEscalation entity);

    ClientOpsEscalation toEntity(ClientOpsEscalationDTO dto);

    ClientOpsEscalationDTO toDTO(ClientOpsEscalation entity);

    ClientTechEscalation toEntity(ClientTechEscalationDTO dto);

    ClientTechEscalationDTO toDTO(ClientTechEscalation entity);

    Packing toEntity(PackingDTO dto);

    PackingDTO toDTO(Packing entity);

    Linehaul toEntity(LinehaulDTO dto);

    LinehaulDTO toDTO(Linehaul entity);

    Vehicle toEntity(VehicleDTO dto);

    VehicleDTO toDTO(Vehicle entity);

    Labour toEntity(LabourDTO dto);

    LabourDTO toDTO(Labour entity);

    Product toEntity(ProductDTO dto);

    ProductDTO toDTO(Product entity);

    TicketsFollow toEntity(TicketFollowDTO dto);

    TicketFollowDTO toDTO(TicketsFollow entity);

    Payment toEntity(PaymentDTO dto);

    PaymentDTO toDTO(Payment entity);
}
