package br.com.zonesoftware.apicombustivel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 *
 * @author daniel
 */
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Combustivel implements BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(length = 65)
    private String descricao;

    @NotNull
    @Size(min = 1)
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "combustivel_tipos_combustiveis",
            joinColumns = {@JoinColumn(name = "combustivel_id")},
            inverseJoinColumns = {@JoinColumn(name = "tipo_combustivel_id")})
    private List<TipoCombustivel> tipos;

}
