package br.com.zonesoftware.apicombustivel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.model.TipoCombustivel;
import br.com.zonesoftware.apicombustivel.repository.TipoCombustivelRepository;

@Service
public class TipoCombustivelService extends AbstractService<TipoCombustivel> {

    @Autowired
    private TipoCombustivelRepository repository;

    @Override
    public TipoCombustivelRepository getRepository() {
        return repository;
    }

	@Override
	public Page<TipoCombustivel> findAllPaged(String value, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
