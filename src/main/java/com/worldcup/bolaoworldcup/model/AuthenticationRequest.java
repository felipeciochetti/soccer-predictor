package com.worldcup.bolaoworldcup.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationRequest {
	
	
	private String email;
	private String passWord;
	
	
}
