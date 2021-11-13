package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import br.com.zonesoftware.apicombustivel.model.Fabricante;
import br.com.zonesoftware.apicombustivel.model.Modelo;
import br.com.zonesoftware.apicombustivel.service.FabricanteService;
import br.com.zonesoftware.apicombustivel.service.ModeloService;

import java.util.Arrays;

@Controller
@RequestMapping("/modelos")
@CrossOrigin(origins = "http://localhost:4200")
public class ModeloController extends AbstractController<Modelo> {

    @Value("${fipe.modelos}")
    private String URL_RESOURCE;

    @Autowired
    private FabricanteService fabricanteService;

    @Autowired
    private ModeloService modeloService;

    @Override
    protected ModeloService getService() {
        return modeloService;
    }

    @GetMapping("/buscar/{fabricanteId}/{nome}")
    public ResponseEntity getModelos(@PathVariable Long fabricanteId, @PathVariable String nome) {
        Fabricante fabricante = fabricanteService.findById(fabricanteId).orElse(null);
        if (fabricante == null) {
            return ResponseEntity.badRequest().body(getGlobalError("Ops! Fabricante não encontrado"));
        }
        return ResponseEntity.ok(getService().findByFabricanteAndNameContaining(fabricante, nome));
    }


    @GetMapping("/importar")
    public void importar() {
        fabricanteService.findAll().forEach(f -> {
            var restTemplate = new RestTemplate();
            Modelo[] modelos = restTemplate
                    .getForObject(URL_RESOURCE.replace(":id", f.getId().toString()), Modelo[].class);
            Arrays.stream(modelos).forEach(m -> {
                if (!getService().findById(f.getId()).isPresent()) {
                    m.setFabricante(f);
                    getService().save(m);
                }
            });
        });
    }

}
