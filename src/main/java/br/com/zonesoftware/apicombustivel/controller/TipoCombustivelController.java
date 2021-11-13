package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zonesoftware.apicombustivel.model.TipoCombustivel;
import br.com.zonesoftware.apicombustivel.service.TipoCombustivelService;

@RestController
@RequestMapping("/tipos-combustiveis")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoCombustivelController extends AbstractController<TipoCombustivel> {

    @Autowired
    private TipoCombustivelService service;

    @Override
    public TipoCombustivelService getService() {
        return service;
    }

}
