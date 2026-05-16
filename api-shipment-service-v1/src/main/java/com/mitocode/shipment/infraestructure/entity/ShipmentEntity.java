package com.mitocode.shipment.infraestructure.entity;

import com.mitocode.shipment.domain.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "shipments")
public class ShipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID orderId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = true)
    private String reference;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shipment_company_id", nullable = false)
    private ShipmentCompanyEntity shipmentCompany;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

}
