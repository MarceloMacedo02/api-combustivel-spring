package br.com.zonesoftware.apicombustivel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.model.Combustivel;
import br.com.zonesoftware.apicombustivel.repository.CombustivelRepository;

@Service
public class CombustivelService extends AbstractService<Combustivel> {

    @Autowired
    private CombustivelRepository repository;

    @Override
    public CombustivelRepository getRepository() {
        return repository;
    }

	@Override
	public Page<Combustivel> findAllPaged(String value, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
