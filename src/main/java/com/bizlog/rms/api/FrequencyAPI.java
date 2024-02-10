package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.frequency.FrequencyDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequestMapping(value = "/api/v1/cos/{clientId}/frequency")
public interface FrequencyAPI extends BaseAPI<FrequencyDTO, FrequencyDTO> {
    @PostMapping
    ResponseEntity<FrequencyDTO> create(Long clientId, FrequencyDTO frequencyDTO);

    @GetMapping("/{id}")
    ResponseEntity<FrequencyDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<FrequencyDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<FrequencyDTO> update(Long Id, Long id, FrequencyDTO frequencyDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/search")
    ResponseEntity<PageResponse<FrequencyDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Optional<Set<String>> attributesOpt, Pageable pageable);

    @GetMapping("/advanceSearch")
    ResponseEntity<PageResponse<FrequencyDTO>> advanceSearch(@RequestParam("search") String search,
            @RequestParam("requiredAttributes") Optional<Set<String>> attributesOpt, Pageable pageable);
    @GetMapping("/getByClientId")
    ResponseEntity<FrequencyDTO> getByClientId(Long clientId);
}
