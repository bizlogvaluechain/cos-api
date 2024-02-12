package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.ActivitySOPDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/sop/activity")
public interface ActivityAPI extends BaseAPI<ActivitySOPDTO,ActivitySOPDTO>{

    @PostMapping
    ResponseEntity<ActivitySOPDTO> create(Long clientId, ActivitySOPDTO inputDTOS);

    @GetMapping("/{id}")
    ResponseEntity<ActivitySOPDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ActivitySOPDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ActivitySOPDTO> update(Long Id, Long id, ActivitySOPDTO inputDTOs);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
    @GetMapping("/getByClientId")
    ResponseEntity<ActivitySOPDTO> getByClientId(Long clientId);
}
