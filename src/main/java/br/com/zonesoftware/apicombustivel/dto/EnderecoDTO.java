package br.com.zonesoftware.apicombustivel.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id") 
public class EnderecoDTO implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;

	 
	private String rua;

	 
	private String numero;
 
	private String bairro;
 
	private String cep;

}
