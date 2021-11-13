package br.com.zonesoftware.apicombustivel.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.zonesoftware.apicombustivel.model.PerfilAcesso;
import br.com.zonesoftware.apicombustivel.model.Usuario;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static UserSS create(Usuario u) {
        return new UserSS(u.getId(), u.getEmail(), u.getSenha(),
                mapToGrantedAuthorities(u.getPerfil()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilAcesso perfil) {
        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(perfil.toString()));
        return authorities;
    }

}
