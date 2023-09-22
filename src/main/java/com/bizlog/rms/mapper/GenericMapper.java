package com.bizlog.rms.mapper;

import com.bizlog.rms.dto.OrganizationDTO;
import com.bizlog.rms.dto.OrganizationSettingDTO;
import com.bizlog.rms.dto.users.UserDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.OrganizationSetting;
import com.bizlog.rms.entities.users.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {  }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GenericMapper {

    OrganizationDTO toDTO(Organization entity);

    Organization toEntity(OrganizationDTO dto);

    OrganizationSettingDTO toDTO(OrganizationSetting entity);

    OrganizationSetting toEntity(OrganizationSettingDTO dto);


    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);
}