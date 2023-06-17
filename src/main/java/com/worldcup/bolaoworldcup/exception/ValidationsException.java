package com.worldcup.bolaoworldcup.exception;

public class ValidationsException  extends  RuntimeException {
	
	
	public ValidationsException(String message) {

        super(String.format(message));
    }

}
