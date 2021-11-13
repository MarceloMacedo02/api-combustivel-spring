package br.com.zonesoftware.apicombustivel.service.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zonesoftware.apicombustivel.model.Usuario;
import br.com.zonesoftware.apicombustivel.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.MethodNotAllowedException;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Base64;

/**
 * @author arkson on 2018/11/01
 */

@Service
public class RequestService {

    private UsuarioRepository usuarioRepository;
    private RestTemplate restTemplate;

    @Value("${app.api.url}")
    private String apiUrl;

    @Autowired
    public RequestService(UsuarioRepository usuarioRepository, RestTemplate restTemplate) {
        this.usuarioRepository = usuarioRepository;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init () {
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    /**
     * Used to get data from API
     *
     * @param url
     * @param responseType
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> request(String url, Class<T> responseType) {
        RequestEntity requestEntity = new RequestEntity(basicAuthHeader(), HttpMethod.GET, createURI(url));

        ResponseEntity<T> responseEntity = restTemplate.exchange(requestEntity, responseType);

        return responseEntity;
    }

    /**
     * Used to send data to API
     *
     * @param url
     * @param method
     * @param responseType
     * @param requestBody
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> request(String url, HttpMethod method, Class<T> responseType, @NonNull Object requestBody) {
        if (method.name().equalsIgnoreCase(HttpMethod.GET.name())) {
            throw new MethodNotAllowedException(HttpMethod.GET, null);
        }

        HttpEntity<Object> httpEntity = new HttpEntity(requestBody, basicAuthHeader());

        ResponseEntity<T> responseEntity = restTemplate.exchange(apiUrl + url, method, httpEntity, responseType);

        return responseEntity;
    }

    private MultiValueMap<String, String> basicAuthHeader() {
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Authorization", "Basic " + credentialsBase64());

        return header;
    }

    private String credentialsBase64() {
        Usuario usuario = usuarioRepository.findByEmail("admin");
        String credentials = usuario.getEmail().concat(":").concat(usuario.getSenha());
        return new String(Base64.getEncoder().encode(credentials.getBytes()));
    }

    private URI createURI(String url) {
        return URI.create(apiUrl + url);
    }
}
