package br.com.zonesoftware.apicombustivel.dto;

import java.io.Serializable;

import br.com.zonesoftware.apicombustivel.dto.VeiculoDTO.Conservacao;
import br.com.zonesoftware.apicombustivel.model.Veiculo;
import lombok.Data;

@Data
public class VeiculoSampleDTO implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String placa;
	private String modelo ;
	private String cor;
	private String tombamento;
	private String ativo;
	private String responsavel ;
	private  br.com.zonesoftware.apicombustivel.model.Veiculo.Conservacao conservacao;
	private String ano;
	private String setor ;
	public VeiculoSampleDTO(Veiculo v) {
		super();
		this.id = v.getId();
		this.placa = v.getPlaca();
		this.modelo = v.getVeiculoFipe().getModelo();
		this.cor = v.getCor();
		this.tombamento = v.getTombamento();
		this.ativo = v.getAtivo();
		this.responsavel = v.getResponsavel().getNome();
		this.conservacao = v.getConservacao();
		this.ano = v.getVeiculoFipe().getCodigoano();
		this.setor = v.getSetor().getNome();
	}
	
	
}
