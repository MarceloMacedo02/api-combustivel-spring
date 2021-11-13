package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zonesoftware.apicombustivel.model.Suprimento;
import br.com.zonesoftware.apicombustivel.service.SuprimentoService;

@RestController
@RequestMapping("/suprimentos")
@CrossOrigin(origins = "http://localhost:4200")
public class SuprimentoController extends AbstractController<Suprimento> {

    @Autowired
    private SuprimentoService service;

    @Override
    public SuprimentoService getService() {
        return service;
    }

}
