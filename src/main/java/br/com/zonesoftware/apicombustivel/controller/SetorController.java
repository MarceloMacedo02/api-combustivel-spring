package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.zonesoftware.apicombustivel.model.Fabricante;
import br.com.zonesoftware.apicombustivel.model.Setor;
import br.com.zonesoftware.apicombustivel.service.SetorService;

import java.util.List;

@RestController
@RequestMapping("/setores")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = {"http://localhost:3000"})
public class SetorController extends AbstractController<Setor> {

    @Autowired
    private SetorService service;

    @Override
    public SetorService getService() {
        return service;
    }

    @GetMapping("/buscar/{nome}")
    public @ResponseBody
    List<Setor> getSetores(@PathVariable String nome) {
        return service.findByNomeContaining(nome);
    }

}
