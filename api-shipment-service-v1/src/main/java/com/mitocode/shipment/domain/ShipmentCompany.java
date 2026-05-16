package com.mitocode.shipment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShipmentCompany {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String webSite;

    public ShipmentCompany(Long id) {
        this.id = id;
    }
}
