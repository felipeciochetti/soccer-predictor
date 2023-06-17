package com.worldcup.bolaoworldcup.view;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserPointsView {


	private Long id;
	private String userName;	
	private Long points;


}
