package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.zonesoftware.apicombustivel.dto.VeiculoSampleDTO;
import br.com.zonesoftware.apicombustivel.model.Veiculo;
import br.com.zonesoftware.apicombustivel.service.VeiculoService;
import br.com.zonesoftware.apicombustivel.service.integration.RequestService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin(origins = "http://localhost:4200")
public class VeiculoController extends AbstractController<Veiculo> {

    @Autowired
    private VeiculoService service;

    @Override
    public VeiculoService getService() {
        return service;
    }

    @GetMapping("/buscar/{placa}")
    public @ResponseBody
    List<Veiculo> getVeiculos(@PathVariable String placa) {
        return service.findByPlacaContainingIgnoreCase(placa);
    }
    @GetMapping("/pageableveiculo")
	public ResponseEntity<Page<?>> findAllPagedStatus(
			@RequestParam(value = "value",	defaultValue = "") String value,
			@RequestParam(value = "ativo",	defaultValue = "Sim") String ativo,
			Pageable pageable) {
		Page<VeiculoSampleDTO> list = getService().findAllPagedStatus( value.trim(),ativo, pageable);
		return ResponseEntity.ok().body(list);
	}
}
