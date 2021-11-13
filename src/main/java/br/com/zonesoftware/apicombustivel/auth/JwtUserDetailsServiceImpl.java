package br.com.zonesoftware.apicombustivel.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zonesoftware.apicombustivel.service.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var user = usuarioService.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException(String.format("Nenhum usuário encontrado com email '%s'", email));
        }

        return JwtUserFactory.create(user);

    }
}
