package br.com.zonesoftware.apicombustivel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "id") 
public class ItemManutencaoDTO implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;
    private Long id;
 
    @JsonIgnore
    private ManutencaoDTO manutencanew =new ManutencaoDTO();

    @NotNull
    
    private SuprimentoDTO suprimento;

    @DecimalMin(value = "0.1")
    private BigDecimal quantidade;

    @DecimalMin(value = "0.1")
    private BigDecimal valor;

}
