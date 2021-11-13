package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.zonesoftware.apicombustivel.model.Usuario;
import br.com.zonesoftware.apicombustivel.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController extends AbstractController<Usuario> {

    @Autowired
    private UsuarioService service;

    public UsuarioService getService() {
        return service;
    }

    @GetMapping("/buscar/{nome}")
    public @ResponseBody
    List<Usuario> getUsuarios(@PathVariable String nome) {
        return service.findByNomeContaining(nome);
    }

}
