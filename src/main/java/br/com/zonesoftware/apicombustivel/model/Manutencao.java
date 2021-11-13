package br.com.zonesoftware.apicombustivel.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author daniel
 */

@Data
@Entity
public class Manutencao extends OperacaoVeiculo {

    @NotNull
    @Size(min = 1)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manutencao")
    private List<ItemManutencao> itens;

    @Override
    public String toString() {
        return getId() + " - " + getCredor();
    }
}
