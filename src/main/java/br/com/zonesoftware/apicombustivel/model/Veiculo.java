package br.com.zonesoftware.apicombustivel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.zonesoftware.apicombustivel.model.convertrs.SimNaoConverter;
import br.com.zonesoftware.apicombustivel.model.enuns.SimNaoEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Data
@EqualsAndHashCode(of = { "id", "placa" })
@Entity
public class Veiculo implements BaseEntity {

	public static enum Conservacao {
		BOM, REGULAR, RUIM
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(length = 10, unique = true)
	private String placa;

//	@NotNull
	@ManyToOne
	@JoinColumn(name = "modelo_id")
	private Modelo modelo;

	@NotEmpty
	private String descricao;

	private String chassi;

	private String cor;

	private String renavan;

	private String observacao;

	private String rota;

	private String tombamento;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate aquisicao;
	
	@Convert(converter = SimNaoConverter.class)
	private String maquinario;

	@Convert(converter = SimNaoConverter.class)
	private String ativo;

	@Convert(converter = SimNaoConverter.class)
	private String alugado;

	private float tanque;

	@Column(name = "consumo_medio")
	private float consumoMedio;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "responsavel_id")
	private Usuario responsavel;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Conservacao conservacao;

	/**
	 * pode ser ano/ano
	 */
//	@NotEmpty
	private String ano;

	@NotNull
	@OneToOne
	private Setor setor;

//	@NotNull
	@ManyToOne
	@JoinColumn(name = "tipo_combustivel_id")
	private TipoCombustivel tipoCombustivel;

	@Column(unique = true)
	private String imei;

	@Column(unique = true)
	private String chip;

	private Long deviceId;

	public Veiculo() {
		this.ativo = SimNaoEnum.Sim.getDescricao();
	}

	@Embedded
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@Column(name = "id", nullable = true, insertable = true, updatable = true)
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
