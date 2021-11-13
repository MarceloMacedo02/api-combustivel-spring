package br.com.zonesoftware.apicombustivel.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Summary {

    private BigDecimal distance;
    private BigDecimal maxSpeed;
    private BigDecimal averageSpeed;

}
