package com.bizlog.rms.mapper;

import com.bizlog.rms.dto.ClientDTO;
import com.bizlog.rms.dto.ClientSettingDTO;
import com.bizlog.rms.dto.CustomerInfoDTO;
import com.bizlog.rms.dto.SOP_TAT.SOPActivityDTO;
import com.bizlog.rms.dto.SOP_TAT.TATActivityDTO;
import com.bizlog.rms.dto.SOP_TAT.TicketCreationConfigDTO;
import com.bizlog.rms.dto.escalationMatrix.EscalationMatrixDTO;
import com.bizlog.rms.dto.frequency.FrequencyDTO;
import com.bizlog.rms.dto.locationService.LocationDTO;
import com.bizlog.rms.dto.notification.NotificationDTO;
import com.bizlog.rms.dto.productInformation.ProductInformationDTO;
import com.bizlog.rms.dto.users.UserDTO;
import com.bizlog.rms.entities.ClientSetting;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.CustomerInfo;
import com.bizlog.rms.entities.Specifications.SOPActivity;
import com.bizlog.rms.entities.Specifications.TATActivity;
import com.bizlog.rms.entities.escalationMatrix.EscalationMatrix;
import com.bizlog.rms.entities.frequency.Frequency;
import com.bizlog.rms.entities.location.Location;
import com.bizlog.rms.entities.notification.Notification;
import com.bizlog.rms.entities.productInformation.ProductInformation;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationConfig;
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
}
