package br.com.zonesoftware.apicombustivel.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class Pessoa implements BaseEntity{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(length = 25)
	private String documento;

	@Column(length = 80)
	private String nome;
	
//	@OneToOne(cascade={CascadeType.ALL})
	@Embedded 
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@Column(name = "id", nullable = true, insertable = true, updatable = true)
	private Endereco endereco = new Endereco();

//	@OneToOne(cascade={CascadeType.ALL})
	@Embedded 
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@Column(name = "id", nullable = true, insertable = true, updatable = true)
	private Contato contato = new Contato();

}
