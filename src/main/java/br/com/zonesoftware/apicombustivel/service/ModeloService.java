package br.com.zonesoftware.apicombustivel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.model.Fabricante;
import br.com.zonesoftware.apicombustivel.model.Modelo;
import br.com.zonesoftware.apicombustivel.repository.ModeloRepository;

import java.util.List;

@Service
public class ModeloService extends AbstractService<Modelo> {

    @Autowired
    private ModeloRepository repository;

    @Override
    public ModeloRepository getRepository() {
        return repository;
    }

    public List<Modelo> findByFabricanteAndNameContaining(Fabricante fabricante, String nome){
        return getRepository().findByFabricanteAndNameContainingIgnoreCase(fabricante, nome);
    }

	@Override
	public Page<Modelo> findAllPaged(String value, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
