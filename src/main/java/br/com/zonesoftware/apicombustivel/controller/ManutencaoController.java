package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.zonesoftware.apicombustivel.model.Manutencao;
import br.com.zonesoftware.apicombustivel.service.ManutencaoService;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/manutencoes")
@CrossOrigin(origins = "http://localhost:4200")
public class ManutencaoController extends AbstractController<Manutencao> {

    @Autowired
    private ManutencaoService service;

    @Override
    public ManutencaoService getService() {
        return service;
    }

    @Override
    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid Manutencao entity, BindingResult errors) {

        if(entity != null && entity.getItens() != null){
            entity.getItens().forEach(i -> i.setManutencao(entity));
        }

        return super.salvar(entity, errors);
    }

}
