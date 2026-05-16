package com.mitocode.shipment.repository;

import com.mitocode.shipment.domain.ShipmentCompany;
import com.mitocode.shipment.infraestructure.repository.ShipmentCompanyRepositoryJpa;
import com.mitocode.shipment.repository.mapper.ShipmentCompanyMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ShipmentCompanyRepository {

    private final ShipmentCompanyRepositoryJpa shipmentCompanyRepository;

    public Optional<ShipmentCompany> getById(Long id) {
        return shipmentCompanyRepository.findById(id).map(ShipmentCompanyMapper::toDomain);
    }

}
