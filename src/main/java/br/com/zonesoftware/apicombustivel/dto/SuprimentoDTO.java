package br.com.zonesoftware.apicombustivel.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(of = "id") 
public class SuprimentoDTO  implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty 
    private String descricao;

}
