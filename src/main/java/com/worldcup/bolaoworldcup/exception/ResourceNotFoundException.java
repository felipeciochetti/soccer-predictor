package com.worldcup.bolaoworldcup.exception;

public class ResourceNotFoundException  extends  RuntimeException {
	
	
	public ResourceNotFoundException(String message) {

        super(String.format(message + " not found"));
    }

}
