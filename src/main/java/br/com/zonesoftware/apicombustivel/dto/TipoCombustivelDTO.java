package br.com.zonesoftware.apicombustivel.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author daniel
 */
@Data
@EqualsAndHashCode(of = "id") 
public class TipoCombustivelDTO implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotEmpty 
	private String descricao;

}
