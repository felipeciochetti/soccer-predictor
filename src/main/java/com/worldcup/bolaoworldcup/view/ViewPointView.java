package com.worldcup.bolaoworldcup.view;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ViewPointView {


	private String home;
	private String visitor;
	
	private Long scoreHome;
	private Long scoreVisitor;
	
	private Long palpiteScoreHome;
	private Long palpiteScoreVisitor;
	
	private Long points;
	
	private String description;
	
	private String urlHomeImagem;
	private String urlVisitorImagem;

	
}
