package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.LocationCenterAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.LocationCenterDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.product.LocationCenter;
import com.bizlog.rms.entities.product.ProductInfo;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.repository.LocationCenterRepository;
import com.bizlog.rms.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationCenterResource extends BaseClientResource<LocationCenter, LocationCenterDTO,LocationCenterDTO> implements LocationCenterAPI {
    public LocationCenterResource(BaseClientRepository<LocationCenter, Long> baseClientRepository) {
        super(baseClientRepository);
    }
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LocationCenterRepository locationCenterRepository;

    @Override
    public ResponseEntity<LocationCenterDTO> create(@PathVariable("clientId") Long clientId,
                                              @RequestBody LocationCenterDTO payloadDTO) {
        ProductInfo product = productInfoRepository.findById(payloadDTO.getProductInfoId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found", "id", clientId));
        Client client= clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        LocationCenter locationCenter =toEntity(payloadDTO);
        locationCenter.setClient(client);
        locationCenter.setProductInfo(product);
        LocationCenter savedLocationCenter = locationCenterRepository.save(locationCenter);
        LocationCenterDTO savedLocationCenterDTO = toDTO(savedLocationCenter);
        return new ResponseEntity<>(savedLocationCenterDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LocationCenterDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                              @RequestBody LocationCenterDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<LocationCenterDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<LocationCenterDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected LocationCenterDTO toDTO(LocationCenter entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected LocationCenter toEntity(LocationCenterDTO dto) {
        return getMapper().toEntity(dto);
    }
}
