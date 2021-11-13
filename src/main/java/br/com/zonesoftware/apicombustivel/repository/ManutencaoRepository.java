package br.com.zonesoftware.apicombustivel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zonesoftware.apicombustivel.model.Manutencao;
import br.com.zonesoftware.apicombustivel.model.Usuario;
import br.com.zonesoftware.apicombustivel.model.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    List<Manutencao> findByVeiculo(Veiculo v);

    List<Manutencao> findByCredor(Veiculo v);

    List<Manutencao> findByDataRealizacaoBetween(LocalDateTime inicio, LocalDateTime fim);

}
