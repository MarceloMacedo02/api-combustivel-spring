package br.com.zonesoftware.apicombustivel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Usuario extends Pessoa {

 
	private static final long serialVersionUID = 1L;

	@NotEmpty
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @NotEmpty
    private String senha;

    @NotNull
    @Enumerated
    private PerfilAcesso perfil;

}
