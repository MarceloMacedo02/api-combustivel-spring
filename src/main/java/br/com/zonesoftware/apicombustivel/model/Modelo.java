package br.com.zonesoftware.apicombustivel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Modelo implements BaseEntity {

    @Id
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;

}
