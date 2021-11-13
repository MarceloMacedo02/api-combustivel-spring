package br.com.zonesoftware.apicombustivel.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zonesoftware.apicombustivel.model.TipoCombustivel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author daniel
 */
@Data
@EqualsAndHashCode(of = "id") 
public class CombustivelDTO implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;
     
    private Long id;
 
    private String descricao;
 
  
    private List<TipoCombustivelDTO> tipos=new ArrayList<>();

}
