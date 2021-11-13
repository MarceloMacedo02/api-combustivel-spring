package br.com.zonesoftware.apicombustivel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zonesoftware.apicombustivel.model.VeiculoFipe;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@EqualsAndHashCode(of = { "id", "placa" })
public class VeiculoDTO implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;

	public static enum Conservacao {
		BOM, REGULAR, RUIM
	}
 
	private Long id;
  
	private String placa;
 
	private ModeloDTO modelo=new ModeloDTO();
 
	private String descricao;

	private String chassi;

	private String cor;

	private String renavan;

	private String observacao;

	private String rota;

	private String tombamento;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate aquisicao;

	private String maquinario;

	private String ativo;

	private String alugado;

	private float tanque;
 
	private float consumoMedio;
 
	private UsuarioDTO responsavel=new UsuarioDTO();
 
	private Conservacao conservacao;

 
	private String ano;

	@NotNull 
	private SetorDTO setor=new SetorDTO();
 
	private TipoCombustivelDTO tipoCombustivel=new TipoCombustivelDTO();

 
	private String imei;
 
	private String chip;

	private Long deviceId;
	private VeiculoFipe veiculoFipe;
 
	@Data
	@Builder
	public static class Device {

		private Long id;
		private String name;
		private String uniqueId;
		private String disabled;
		private String phone;

	}

}
