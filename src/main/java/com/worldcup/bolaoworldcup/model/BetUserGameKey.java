package com.worldcup.bolaoworldcup.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class BetUserGameKey implements Serializable {
	
	
	Long userId;

	Long gameId;
	
	

	
	
}
