package br.com.zonesoftware.apicombustivel.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(of = "id") 
public class SetorDTO   implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotEmpty
	private String nome;

}
