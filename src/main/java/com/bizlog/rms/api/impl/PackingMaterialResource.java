package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.PackingMaterialAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.PackingMaterialDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.product.PackingMaterial;
import com.bizlog.rms.entities.product.ProductInfo;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.repository.PackingMaterialRepository;
import com.bizlog.rms.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackingMaterialResource extends BaseClientResource<PackingMaterial, PackingMaterialDTO,PackingMaterialDTO> implements PackingMaterialAPI {
    public PackingMaterialResource(BaseClientRepository<PackingMaterial, Long> baseClientRepository) {
        super(baseClientRepository);
    }
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PackingMaterialRepository packingMaterialRepository;
    @Override
    public ResponseEntity<PackingMaterialDTO> create(@PathVariable("clientId") Long clientId,
                                              @RequestBody PackingMaterialDTO payloadDTO) {
        ProductInfo product = productInfoRepository.findById(payloadDTO.getProductInfoId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found", "id", clientId));
        Client client= clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        PackingMaterial packingMaterial =toEntity(payloadDTO);
        packingMaterial.setClient(client);
        packingMaterial.setProductInfo(product);
        PackingMaterial savedPackingMaterial = packingMaterialRepository.save(packingMaterial);
        PackingMaterialDTO savedPackingMaterialDTO = toDTO(savedPackingMaterial);
        return new ResponseEntity<>(savedPackingMaterialDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PackingMaterialDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                              @RequestBody PackingMaterialDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<PackingMaterialDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<PackingMaterialDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected PackingMaterialDTO toDTO(PackingMaterial entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected PackingMaterial toEntity(PackingMaterialDTO dto) {
        return getMapper().toEntity(dto);
    }
}
