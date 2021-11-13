package br.com.zonesoftware.apicombustivel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zonesoftware.apicombustivel.model.Fabricante;

import java.util.List;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

    List<Fabricante> findByNameContainingIgnoreCase(String name);

}
