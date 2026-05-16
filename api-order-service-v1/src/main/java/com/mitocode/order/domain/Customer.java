package com.mitocode.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
public class Customer {

    private Long id;
    private String name;
}
