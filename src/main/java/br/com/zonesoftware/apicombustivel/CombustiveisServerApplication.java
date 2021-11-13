package br.com.zonesoftware.apicombustivel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import br.com.zonesoftware.apicombustivel.model.PerfilAcesso;
import br.com.zonesoftware.apicombustivel.model.Usuario;
import br.com.zonesoftware.apicombustivel.service.UsuarioService;


@SpringBootApplication
public class CombustiveisServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CombustiveisServerApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UsuarioService usuarioService, PasswordEncoder passwordEncoder){
        return args -> {
            initUser(usuarioService, passwordEncoder);
        };
    }

    private void initUser(UsuarioService usuarioService, PasswordEncoder passwordEncoder){

        Usuario usuario=null;
        usuario =new Usuario();// Usuario.builder()
        usuario.setEmail("admin@gmail.com");
        usuario.setSenha(passwordEncoder.encode("123456"));
        usuario.setPerfil(PerfilAcesso.SUPER_ADMINISTRADOR);
              // .build();

        usuario.setNome("Fulano de Tal dos Anzóis");
        usuario.setDocumento("20073940326");

        if(usuarioService.findByEmail(usuario.getEmail()) == null){
            usuarioService.save(usuario);
        }
//        for (int i = 0; i < 30;i++) {
//        	usuario =new Usuario();// Usuario.builder()
//            usuario.setEmail("admin@gmail.com" + ""+i);
//            usuario.setSenha(passwordEncoder.encode("123456"));
//            usuario.setPerfil(PerfilAcesso.SUPER_ADMINISTRADOR);
//                  // .build();
//
//            usuario.setNome("Fulano de Tal dos Anzóis" + ""+i);
//            usuario.setDocumento("20073940326-"+ ""+i);
//
//            if(usuarioService.findByEmail(usuario.getEmail()) == null){
//                usuarioService.save(usuario);
//            }
//		}
//        

    }

    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }

}
