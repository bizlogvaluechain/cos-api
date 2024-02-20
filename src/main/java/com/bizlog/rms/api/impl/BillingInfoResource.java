package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.BillingInfoAPI;
import com.bizlog.rms.dto.clientinfo.BillingInfoDTO;
import com.bizlog.rms.entities.clientinfo.BillingInfo;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;

import com.bizlog.rms.repository.BillingInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BillingInfoResource implements BillingInfoAPI {
    private final BillingInfoRepository billingInfoRepository;
    private final GenericMapper genericMapper;

    @Autowired
    public BillingInfoResource(BillingInfoRepository billingInfoRepository, GenericMapper genericMapper) {
        this.billingInfoRepository = billingInfoRepository;
        this.genericMapper = genericMapper;
    }

    @Override
    public ResponseEntity<List<BillingInfoDTO>> create(@RequestBody List<BillingInfoDTO> payloadDTO) {
        log.info("Request received to create entity with payloadDTO:{} ", payloadDTO);
        List<BillingInfo> billingInfos = genericMapper.toEntity(payloadDTO);
        List<BillingInfo> billingInfos1 = billingInfoRepository.saveAll(billingInfos);
        List<BillingInfoDTO> billingInfoDTOS = genericMapper.toDTO(billingInfos1);

        return new ResponseEntity<>(billingInfoDTOS, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        log.info("Request received to delete entity with id:{} ", id);
        billingInfoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<BillingInfoDTO> getById(Long id) {
        log.info("Request received to get entity with id:{} ", id);
        BillingInfo existingBillingInfo = billingInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BillingInfo not found with  ", "id", id));
        BillingInfoDTO dto = genericMapper.toDTO(existingBillingInfo);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BillingInfoDTO> update(@PathVariable Long id,
            @RequestBody BillingInfoDTO updatedBillingInfoDTO) {
        log.info("Request received to update entity with id:{} and updatedBillingInfoDTO: {} ", id,updatedBillingInfoDTO );

        BillingInfo existingBillingInfo = billingInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BillingInfo not found with id", "id", id));
        existingBillingInfo.setCity(updatedBillingInfoDTO.getCity());
        existingBillingInfo.setCountry(updatedBillingInfoDTO.getCountry());
        existingBillingInfo.setState(updatedBillingInfoDTO.getState());
        existingBillingInfo.setPincode(updatedBillingInfoDTO.getPincode());
        BillingInfo updatedBillingInfoEntity = billingInfoRepository.save(existingBillingInfo);
        BillingInfoDTO updatedBillingInfoDTOs = genericMapper.toDTO(updatedBillingInfoEntity);
        return new ResponseEntity<>(updatedBillingInfoDTOs, HttpStatus.OK);
    }

}
