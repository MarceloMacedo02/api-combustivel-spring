package br.com.zonesoftware.apicombustivel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author daniel
 */
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Abastecimento extends OperacaoVeiculo {

    @NotNull
    private BigDecimal valor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "combustivel_id")
    private Combustivel combustivel;

    @NotNull
    @ManyToOne
    private Setor setor;

}
