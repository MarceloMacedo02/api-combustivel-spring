package br.com.zonesoftware.apicombustivel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.model.Suprimento;
import br.com.zonesoftware.apicombustivel.repository.SuprimentoRepository;

@Service
public class SuprimentoService extends AbstractService<Suprimento> {

    @Autowired
    private SuprimentoRepository repository;

    @Override
    public SuprimentoRepository getRepository() {
        return repository;
    }

	@Override
	public Page<Suprimento> findAllPaged(String value, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
