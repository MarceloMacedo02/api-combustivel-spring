package br.com.zonesoftware.apicombustivel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zonesoftware.apicombustivel.model.PerfilAcesso;
import br.com.zonesoftware.apicombustivel.model.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByPerfil(PerfilAcesso perfil);

    Usuario findByEmail(String email);

    List<Usuario> findByNomeContainingIgnoreCase(String nome);

}
