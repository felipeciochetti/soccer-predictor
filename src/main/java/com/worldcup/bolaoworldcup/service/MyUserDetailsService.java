package com.worldcup.bolaoworldcup.service;

import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService {
	/*
//implements UserDetailsService{
	
	
	@Autowired
	UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuarios user =  repository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User: " + username));


		
		return new User(username, username, new ArrayList<>());
	}
*/
}
