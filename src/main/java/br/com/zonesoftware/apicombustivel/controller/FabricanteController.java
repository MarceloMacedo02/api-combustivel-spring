package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import br.com.zonesoftware.apicombustivel.model.Fabricante;
import br.com.zonesoftware.apicombustivel.service.FabricanteService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/fabricantes")
@CrossOrigin(origins = "http://localhost:4200")
public class FabricanteController extends AbstractController<Fabricante> {

    @Value("${fipe.fabricantes}")
    private String URL_RESOURCE;

    @Autowired
    private FabricanteService service;

    @Override
    public FabricanteService getService() {
        return service;
    }

    @GetMapping("/buscar/{nome}")
    public @ResponseBody
    List<Fabricante> getFabricantes(@PathVariable String nome) {
        return service.findByNameContaining(nome);
    }

    @GetMapping("/importar")
    public void importar() {
        var restTemplate = new RestTemplate();
        Fabricante[] fabricantes = restTemplate.getForObject(URL_RESOURCE, Fabricante[].class);
        Arrays.stream(fabricantes).forEach(f -> {
            if (!service.findById(f.getId()).isPresent()) {
                service.save(f);
            }
        });
    }

}
