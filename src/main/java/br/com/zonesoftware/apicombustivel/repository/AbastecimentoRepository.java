package br.com.zonesoftware.apicombustivel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zonesoftware.apicombustivel.model.Abastecimento;
import br.com.zonesoftware.apicombustivel.model.Usuario;
import br.com.zonesoftware.apicombustivel.model.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {

    List<Abastecimento> findByVeiculo(Veiculo v);

    List<Abastecimento> findByCredor(Veiculo v);

    List<Abastecimento> findByDataRealizacaoBetween(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT SUM(a.valor) from Abastecimento  a")
    BigDecimal getTotal();

}
