package com.bizlog.rms.service;

import com.bizlog.rms.dto.SOP_TAT.SOPActivityDTO;
import com.bizlog.rms.entities.Specifications.SOPActivity;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.SOPActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SOPActivityService {

    private final SOPActivityRepository sopActivityRepository;
    private final GenericMapper sopActivityMapper;

    @Autowired
    public SOPActivityService(SOPActivityRepository sopActivityRepository, GenericMapper sopActivityMapper) {
        this.sopActivityRepository = sopActivityRepository;
        this.sopActivityMapper = sopActivityMapper;
    }

    @Transactional
    public SOPActivityDTO createSOPActivity(SOPActivityDTO sopActivityDTO) {
        SOPActivity sopActivity = sopActivityMapper.toEntity(sopActivityDTO);
        SOPActivity savedSOPActivity = sopActivityRepository.save(sopActivity);
        return sopActivityMapper.toDTO(savedSOPActivity);
    }
}
