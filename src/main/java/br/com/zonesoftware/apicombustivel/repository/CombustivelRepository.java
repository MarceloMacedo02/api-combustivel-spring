package br.com.zonesoftware.apicombustivel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zonesoftware.apicombustivel.model.Combustivel;

@Repository
public interface CombustivelRepository extends JpaRepository<Combustivel, Long> {
}
