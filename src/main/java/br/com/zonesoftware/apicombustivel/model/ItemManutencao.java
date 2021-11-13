package br.com.zonesoftware.apicombustivel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class ItemManutencao implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "manutencao_id")
    @JsonIgnore
    private Manutencao manutencao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "suprimento_id")
    private Suprimento suprimento;

    @DecimalMin(value = "0.1")
    private BigDecimal quantidade;

    @DecimalMin(value = "0.1")
    private BigDecimal valor;

}
