package br.com.zonesoftware.apicombustivel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 *
 * @author daniel
 */
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class TipoCombustivel implements BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(length = 150)
    private String descricao;

}
