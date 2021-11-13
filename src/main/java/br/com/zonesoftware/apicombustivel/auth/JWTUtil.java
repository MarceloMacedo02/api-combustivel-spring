package br.com.zonesoftware.apicombustivel.auth;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zonesoftware.apicombustivel.model.Usuario;
import br.com.zonesoftware.apicombustivel.repository.UsuarioRepository;
import br.com.zonesoftware.apicombustivel.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	private final String USERNAME = "sub";
	private final String CREATED = "created";
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	@Autowired
	UsuarioRepository repository;

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();

		claims.put(USERNAME, username);

		final Date createdAt = new Date();
		claims.put(CREATED, createdAt);

		return doGenerateToken(claims);
//		return Jwts.builder()
//				.setSubject(username)
//				.setExpiration(new Date(System.currentTimeMillis() + expiration))
//				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
//				.compact();
	}

	public String getCurrentUser(String username,String token) {
		// Creating Object of ObjectMapper define in Jakson Api
		ObjectMapper Obj = new ObjectMapper();
		//String email=((UserSS) UserService.authenticated()).getUsername();
		Usuario u = repository.findByEmail(username);
		CurrentUser currentUser=new CurrentUser(token, u);
		String jsonStr="";
		try {

			// get Oraganisation object as a json string
			  jsonStr = Obj.writeValueAsString(currentUser);

			// Displaying JSON String
			System.out.println(jsonStr);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return jsonStr;

	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		claims.put(USERNAME, userDetails.getUsername());

		final Date createdAt = new Date();
		claims.put(CREATED, createdAt);

		return doGenerateToken(claims);

	}

	private String doGenerateToken(Map<String, Object> claims) {
		final Date createdAt = (Date) claims.get(CREATED);
		final Date expirationDate = new Date(createdAt.getTime() + expiration * 1000);
		return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

}
