package br.com.zonesoftware.apicombustivel.dto;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class ContatoDTO implements   BaseDTO,Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;

	@Column(length = 15)
	private String telefone;

}
