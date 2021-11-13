package br.com.zonesoftware.apicombustivel.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zonesoftware.apicombustivel.model.OperacaoVeiculo;
import br.com.zonesoftware.apicombustivel.model.Setor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author daniel
 */
@Data
@EqualsAndHashCode(of = "id") 
public class AbastecimentoDTO   implements BaseDTO,Serializable{
 
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
    private BigDecimal valor;

    @NotNull 
    private CombustivelDTO combustivel=new  CombustivelDTO();

    @NotNull 
    private Setor setor;

}
