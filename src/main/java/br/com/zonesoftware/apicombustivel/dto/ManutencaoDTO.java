package br.com.zonesoftware.apicombustivel.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zonesoftware.apicombustivel.model.Usuario;
import br.com.zonesoftware.apicombustivel.model.Veiculo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel
 */

@Data 
public class ManutencaoDTO implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataRealizacao;

	@NotNull
	private VeiculoDTO veiculo=new VeiculoDTO();

	@NotNull
	private UsuarioDTO credor=new UsuarioDTO();

	private String observacao;

	@NotNull
	private List<ItemManutencaoDTO> itens = new ArrayList<>();

	@Override
	public String toString() {
		return getId() + " - " + getCredor();
	}
}
