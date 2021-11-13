package br.com.zonesoftware.apicombustivel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.model.Manutencao;
import br.com.zonesoftware.apicombustivel.model.Usuario;
import br.com.zonesoftware.apicombustivel.model.Veiculo;
import br.com.zonesoftware.apicombustivel.repository.ManutencaoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManutencaoService extends AbstractService<Manutencao> {

    @Autowired
    private ManutencaoRepository repository;

    @Override
    public ManutencaoRepository getRepository() {
        return repository;
    }

    public List<Manutencao> findByVeiculo(Veiculo v){
        return getRepository().findByVeiculo(v);
    }

    public List<Manutencao> findByCredor(Veiculo v){
        return getRepository().findByCredor(v);
    }

    public List<Manutencao> findByDataRealizacaoBetween(LocalDateTime inicio, LocalDateTime fim){
        return getRepository().findByDataRealizacaoBetween(inicio, fim);
    }

	@Override
	public Page<Manutencao> findAllPaged(String value, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
