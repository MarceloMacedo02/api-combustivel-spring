package br.com.zonesoftware.apicombustivel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.model.Fabricante;
import br.com.zonesoftware.apicombustivel.repository.FabricanteRepository;

@Service
public class FabricanteService extends AbstractService<Fabricante> {

    @Autowired
    private FabricanteRepository repository;

    @Override
    public FabricanteRepository getRepository() {
        return repository;
    }

    public List<Fabricante> findByNameContaining(String name){
        return getRepository().findByNameContainingIgnoreCase(name);
    }

	@Override
	public Page<Fabricante> findAllPaged(String value, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
