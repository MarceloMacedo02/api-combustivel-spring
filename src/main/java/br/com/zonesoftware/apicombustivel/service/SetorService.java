package br.com.zonesoftware.apicombustivel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.dto.VeiculoSampleDTO;
import br.com.zonesoftware.apicombustivel.model.Setor;
import br.com.zonesoftware.apicombustivel.model.Veiculo;
import br.com.zonesoftware.apicombustivel.repository.SetorRepository;

import java.util.List;


@Service
public class SetorService extends AbstractService<Setor> {

    @Autowired
    private SetorRepository repository;

    @Override
    public SetorRepository getRepository() {
        return repository;
    }

    public List<Setor> findByNomeContaining(String nome){
        return getRepository().findByNomeContainingIgnoreCase(nome);
    }

	@Override
	public Page<Setor> findAllPaged(String value, Pageable pageable) {
		Setor u = new Setor();
		u.setNome(value);
		Page<Setor> pages = findAllPaged("nome", u, pageable);
	 
		return pages;
	}

}
