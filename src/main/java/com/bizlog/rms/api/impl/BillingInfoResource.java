package com.bizlog.rms.api.impl;

import com.bizlog.rms.dto.clientinfo.BillingInfoDTO;
import com.bizlog.rms.entities.clientinfo.BillingInfo;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;

import com.bizlog.rms.repository.BillingInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BillingInfoResource {
    @Autowired
    private BillingInfoRepository billingInfoRepository;
    @Autowired
    private GenericMapper genericMapper;

    public ResponseEntity<BillingInfoDTO> create(BillingInfoDTO payloadDTO) {
        BillingInfo entityToSave = genericMapper.toEntity(payloadDTO);
        BillingInfo savedEntity = billingInfoRepository.save(entityToSave);
        BillingInfoDTO savedDTO = genericMapper.toDTO(savedEntity);

        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> delete(Long id) {
        billingInfoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<BillingInfoDTO> getById(Long id) {
        BillingInfo existingEntity = billingInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BillingInfo not found with  ", "id", id));
        BillingInfoDTO dto = genericMapper.toDTO(existingEntity);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
