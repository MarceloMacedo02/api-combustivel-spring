package br.com.zonesoftware.apicombustivel.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.dto.AbastecimentoDTO;
import br.com.zonesoftware.apicombustivel.model.Abastecimento;
import br.com.zonesoftware.apicombustivel.model.Veiculo;
import br.com.zonesoftware.apicombustivel.repository.AbastecimentoRepository;

@Service
public class AbastecimentoService extends AbstractService<Abastecimento> {

	@Autowired
	private AbastecimentoRepository repository;

	@Override
	public AbastecimentoRepository getRepository() {
		return repository;
	}

	public List<Abastecimento> findByVeiculo(Veiculo v) {

		return getRepository().findByVeiculo(v);
	}

	public List<Abastecimento> findByCredor(Veiculo v) {
		return getRepository().findByCredor(v);
	}

	public List<Abastecimento> findByDataRealizacaoBetween(LocalDateTime inicio, LocalDateTime fim) {
		return getRepository().findByDataRealizacaoBetween(inicio, fim);
	}

	public BigDecimal getTotal() {
		return getRepository().getTotal();
	}

	@Override
	public Page<Abastecimento> findAllPaged(String value, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
