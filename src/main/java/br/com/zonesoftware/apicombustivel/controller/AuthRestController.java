package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zonesoftware.apicombustivel.service.UsuarioService;

@RestController
@RequestMapping("api")
public class AuthRestController {
//	@Autowired
//	private JWTUtil jwtUtil;
//    @Autowired
//    private AuthenticationManager authenticationManager;

//	@Autowired
//	private JWTUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UsuarioService usuarioService;

//    @PostMapping("/auth")
//    public ResponseEntity<?> createAuthToken(@RequestBody CredenciaisDTO request) throws Exception {
//
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());
//        final Usuario usuario = usuarioService.findByEmail(request.getEmail());
//        usuario.setSenha(null);
//
//        return ResponseEntity.ok(new CurrentUser(token, usuario));
//
//    }

//	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
//	
//	public ResponseEntity<?> refreshToken(HttpServletResponse response) {
//		String retorno =null;
//	try {	
//		UserSS user = UserService.authenticated();
//		if(user!=null) {
//		String token = jwtTokenUtil.generateToken(user.getUsername());
//		response.addHeader("Authorization", token);
//		response.addHeader("access-control-expose-headers", "Authorization");
//		  retorno = jwtUtil.getCurrentUser(user.getUsername(), token);
//		}
//		} catch (Exception e) {
//		// TODO: handle exception
//	}
//		return ResponseEntity.ok(retorno);
//	}
	/*
	 * @PostMapping("/refresh/{token}") public ResponseEntity<?>
	 * refreshAndGetAuthenticationToken(@PathVariable String token) { JwtUser user =
	 * UserService.authenticated(); String _token =
	 * jwtTokenUtil.generateToken(user.getUsername());
	 * response.addHeader("Authorization", "Bearer " + token);
	 * response.addHeader("access-control-expose-headers", "Authorization"); return
	 * ResponseEntity.noContent().build();
	 * 
	 * String username = jwtTokenUtil.getUsernameFromToken(token);
	 * 
	 * final var usuario = usuarioService.findByEmail(username);
	 * 
	 * if (usuario != null && jwtTokenUtil.canTokenBeRefreshed(token)) { return
	 * ResponseEntity.ok(new CurrentUser(jwtTokenUtil.refreshToken(token),
	 * usuario)); }
	 * 
	 * return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	 */
	// }

}
