package br.com.zonesoftware.apicombustivel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.zonesoftware.apicombustivel.model.PerfilAcesso;
import br.com.zonesoftware.apicombustivel.model.Usuario;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter 
@NoArgsConstructor
public class UsuarioDTO  implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
 
	private String nome;
	 
    private String email;

	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

   

}
