package br.com.zonesoftware.apicombustivel.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
//@EqualsAndHashCode(of = "id")
//@Entity 
@Embeddable
@NoArgsConstructor
public class Endereco {
//	implements BaseEntity{
 
//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private Long id;

	@Column(length = 80)
	private String rua;

	@Column(length = 10)
	private String numero;

	@Column(length = 80)
	private String bairro;

	@Column(length = 10)
	private String cep;

}
