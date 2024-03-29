package com.bizlog.rms.api.impl;

import com.bizlog.rms.dto.clientinfo.CompanyContactDetailsDTO;

import com.bizlog.rms.entities.clientinfo.CompanyContactDetails;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;

import com.bizlog.rms.repository.CompanyContactDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CompanyContactDetailsResource {
    private final CompanyContactDetailsRepository companyContactDetailsRepository;
    private final GenericMapper genericMapper;

    @Autowired
    public CompanyContactDetailsResource(CompanyContactDetailsRepository companyContactDetailsRepository,
            GenericMapper genericMapper) {
        this.companyContactDetailsRepository = companyContactDetailsRepository;
        this.genericMapper = genericMapper;
    }

    public ResponseEntity<CompanyContactDetailsDTO> create(CompanyContactDetailsDTO payloadDTO) {
        log.info("Request received to create an entity with payload: {} ", payloadDTO);
        CompanyContactDetails entityToSave = genericMapper.toEntity(payloadDTO);
        CompanyContactDetails savedEntity = companyContactDetailsRepository.save(entityToSave);
        CompanyContactDetailsDTO savedDTO = genericMapper.toDTO(savedEntity);

        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> delete(Long id) {
        log.info("Request received to delete an entity with entity id: {} ", id);
        companyContactDetailsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<CompanyContactDetailsDTO> getById(Long id) {
        log.info("Request received to create an entity with entity id: {}",getById(id));
        CompanyContactDetails existingEntity = companyContactDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BillingInfo not found with  ", "id", id));
        CompanyContactDetailsDTO dto = genericMapper.toDTO(existingEntity);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
