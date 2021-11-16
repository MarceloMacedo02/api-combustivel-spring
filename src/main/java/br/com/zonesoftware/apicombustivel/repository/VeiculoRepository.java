package br.com.zonesoftware.apicombustivel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zonesoftware.apicombustivel.dto.VeiculoSampleDTO;
import br.com.zonesoftware.apicombustivel.model.Veiculo;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByPlacaContainingIgnoreCase(String placa); 
    Page<Veiculo>  findByPlacaContainingIgnoreCaseAndAtivo(String placa,String ativo,Pageable page);

}
