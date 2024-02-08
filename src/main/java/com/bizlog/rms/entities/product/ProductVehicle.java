package com.bizlog.rms.entities.product;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "product_vehicle_table")
public class ProductVehicle {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "isBizlogVehicleNeededLinehaul", nullable = false)
    private Boolean isBizlogVehicleNeededLinehaul;
    @Column(name = "vehicleType")
    private List<String> vehicleType;
    @Column(name = "isBizlogWarehouseNeeded", nullable = false)
    private Boolean isBizlogWarehouseNeeded;
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "warehouse")
    private String warehouse;
    @Column(name = "is3PLWarehouseRequired", nullable = false)
    private Boolean is3PLWarehouseRequired;

    // @JsonIgnore
    // @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinColumn(name = "Product_Info_Id", nullable = false, updatable = false)
    // private ProductInfo productInfo;
    //
    // @JsonProperty("Product_Info_Id")
    // public Long getProductInfoId() {
    // return (productInfo != null) ? productInfo.getId() : null;
    // }
}
