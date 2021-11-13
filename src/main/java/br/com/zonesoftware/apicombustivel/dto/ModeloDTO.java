package br.com.zonesoftware.apicombustivel.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = { "id" }) 
public class ModeloDTO implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
 
	private String name;
 
	private FabricanteDTO fabricante=new FabricanteDTO();

}
