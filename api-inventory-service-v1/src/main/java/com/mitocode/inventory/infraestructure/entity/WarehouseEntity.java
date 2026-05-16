package com.mitocode.inventory.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "warehouses")
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @Column(name = "warehouse_type", nullable = false)
    private String warehouseType;
}
