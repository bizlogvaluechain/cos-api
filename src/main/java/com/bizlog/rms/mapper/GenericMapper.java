package com.bizlog.rms.mapper;

import com.bizlog.rms.dto.ClientDTO;
import com.bizlog.rms.dto.ClientSettingDTO;
import com.bizlog.rms.entities.ClientSetting;
import com.bizlog.rms.entities.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GenericMapper {

    ClientDTO toDTO(Client entity);

    Client toEntity(ClientDTO dto);

    ClientSettingDTO toDTO(ClientSetting entity);

    ClientSetting toEntity(ClientSettingDTO dto);

}
