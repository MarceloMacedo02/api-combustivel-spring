package br.com.zonesoftware.apicombustivel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
//@EqualsAndHashCode(of = "id")
//@Entity 
@Embeddable
@NoArgsConstructor
public class Contato   {

//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private Long id;

	@Column(length = 15)
	private String telefone;

}
