package br.com.zonesoftware.apicombustivel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author daniel
 */
@Data
@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class OperacaoVeiculo implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_realizacao")
    private LocalDate dataRealizacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "credor_id")
    private Usuario credor;

    private String observacao;

}
