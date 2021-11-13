package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zonesoftware.apicombustivel.model.Abastecimento;
import br.com.zonesoftware.apicombustivel.service.AbastecimentoService;

import java.math.BigDecimal;

@RestController
@RequestMapping("abastecimentos")
public class AbastecimentoController extends AbstractController<Abastecimento> {

    @Autowired
    private AbastecimentoService abastecimentoService;

    @Override
    protected AbastecimentoService getService() {
        return abastecimentoService;
    }

    @GetMapping("/total-gastos")
    public BigDecimal getTotalGastos(){
        return getService().getTotal();
    }

}
