package br.com.zonesoftware.apicombustivel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zonesoftware.apicombustivel.model.TipoCombustivel;

@Repository
public interface TipoCombustivelRepository extends JpaRepository<TipoCombustivel, Long> {
}
