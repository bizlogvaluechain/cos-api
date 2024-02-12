package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.TAT.TATDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping(value = "/api/v1/cos/{clientId}/tat")
public interface TATAPI extends BaseAPI<TATDTO,TATDTO> {
    @PostMapping
    ResponseEntity<TATDTO> create(@PathVariable("clientId") Long clientId,
                                          @RequestBody TATDTO tatdto);

    @GetMapping("/{id}")
    ResponseEntity<TATDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<PageResponse<TATDTO>> getAll(@PathVariable("clientId") Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<TATDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                  TATDTO tatdto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<TATDTO> getByClientId(Long clientId);
}
