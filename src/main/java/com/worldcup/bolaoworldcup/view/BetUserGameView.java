package com.worldcup.bolaoworldcup.view;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BetUserGameView {


	private Long gameId;	
	private Long scoreHome;
	private Long scoreVisitor;


}
