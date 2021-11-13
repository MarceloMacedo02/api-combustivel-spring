package br.com.zonesoftware.apicombustivel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.dto.UsuarioDTO;
import br.com.zonesoftware.apicombustivel.model.PerfilAcesso;
import br.com.zonesoftware.apicombustivel.model.Usuario;
import br.com.zonesoftware.apicombustivel.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService extends AbstractService<Usuario> {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UsuarioRepository getRepository() {
		return repository;
	}

	public List<Usuario> findByNomeContaining(String nome) {
		return getRepository().findByNomeContainingIgnoreCase(nome);
	}

	public List<Usuario> findByPerfil(PerfilAcesso perfil) {
		return getRepository().findByPerfil(perfil);
	}

	public Usuario findByEmail(String email) {
		return getRepository().findByEmail(email);
	}

	@Override
	public Page<UsuarioDTO> findAllPaged(String value, Pageable pageable) {
		Usuario u = new Usuario();
		u.setNome(value);
		Page<Usuario> pages = findAllPaged("nome", u, pageable);
		Page<UsuarioDTO> baseDtos = pages.map(x -> new UsuarioDTO(x));
		return baseDtos;
	}

}
