
package com.worldcup.bolaoworldcup.api;


import java.io.Serializable;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationResource  implements Serializable{

/*

	private static final long serialVersionUID = 1L;
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;

	

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticate(@RequestBody AuthenticationRequest request ) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassWord()));
		
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getEmail());
		
		
		final String jwt = jwtUtil.generateToken(userDetails);

		return new  ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
	}





	*/






}