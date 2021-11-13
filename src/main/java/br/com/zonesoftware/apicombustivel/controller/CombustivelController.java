package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zonesoftware.apicombustivel.model.Combustivel;
import br.com.zonesoftware.apicombustivel.service.CombustivelService;

@RestController
@RequestMapping("/combustiveis")
@CrossOrigin(origins = "http://localhost:4200")
public class CombustivelController extends AbstractController<Combustivel> {

    @Autowired
    private CombustivelService service;

    @Override
    public CombustivelService getService() {
        return service;
    }

}
