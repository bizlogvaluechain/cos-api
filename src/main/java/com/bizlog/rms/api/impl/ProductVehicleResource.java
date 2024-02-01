package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ProductVehicleAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductVehicleDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.product.ProductInfo;
import com.bizlog.rms.entities.product.ProductVehicle;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.repository.ProductInfoRepository;
import com.bizlog.rms.repository.ProductVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductVehicleResource extends BaseClientResource<ProductVehicle, ProductVehicleDTO,ProductVehicleDTO> implements ProductVehicleAPI {
    public ProductVehicleResource(BaseClientRepository<ProductVehicle, Long> baseClientRepository) {
        super(baseClientRepository);
    }
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductVehicleRepository productVehicleRepository;
    @Override
    public ResponseEntity<ProductVehicleDTO> create(@PathVariable("clientId") Long clientId,
                                              @RequestBody ProductVehicleDTO payloadDTO) {
        ProductInfo product = productInfoRepository.findById(payloadDTO.getProductInfoId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found", "id", clientId));
        Client client= clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        ProductVehicle productVehicle =toEntity(payloadDTO);
        productVehicle.setClient(client);
        productVehicle.setProductInfo(product);
        ProductVehicle savedProductVehicle = productVehicleRepository.save(productVehicle);
        ProductVehicleDTO productVehicleDTO = toDTO(savedProductVehicle);
        return new ResponseEntity<>(productVehicleDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductVehicleDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                              @RequestBody ProductVehicleDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<ProductVehicleDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ProductVehicleDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected ProductVehicleDTO toDTO(ProductVehicle entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected ProductVehicle toEntity(ProductVehicleDTO dto) {
        return getMapper().toEntity(dto);
    }
}
