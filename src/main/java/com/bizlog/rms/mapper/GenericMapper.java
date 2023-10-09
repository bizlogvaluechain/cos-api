package com.bizlog.rms.mapper;

import com.bizlog.rms.dto.ClientDTO;
import com.bizlog.rms.dto.ClientSettingDTO;
import com.bizlog.rms.dto.CustomerInfoDTO;
import com.bizlog.rms.dto.Specifications.SOPActivityDTO;
import com.bizlog.rms.dto.Specifications.TATActivityDTO;
import com.bizlog.rms.dto.users.UserDTO;
import com.bizlog.rms.entities.ClientSetting;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.CustomerInfo;
import com.bizlog.rms.entities.Specifications.SOPActivity;
import com.bizlog.rms.entities.Specifications.TATActivity;
import com.bizlog.rms.entities.users.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
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

}
