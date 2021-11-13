package br.com.zonesoftware.apicombustivel.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Embeddable
@NoArgsConstructor
public class VeiculoFipe implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private String valor;
	private String marca;
	private String modelo;
	private String combustivel;
	private String codigoFipe;
	private String mesReferencia;
	private String tipoVeiculo;
	private String siglaCombustivel;
	
	private String codigoano;
	private String codigomodelo;
	private String codigomarca;


}
