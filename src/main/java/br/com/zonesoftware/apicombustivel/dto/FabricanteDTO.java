package br.com.zonesoftware.apicombustivel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(of = {"id"}) 
public class FabricanteDTO  implements BaseDTO, Serializable {

	private static final long serialVersionUID = 1L;
	
    private Long id;
 
    private String name;

    @JsonProperty("fipe_name") 
    private String fipeName;

}
