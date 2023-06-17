package com.worldcup.bolaoworldcup.view;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserPointsPlaceView {

	private Long id;
	private Long place;
	private String userName;	
	private Long points;

}
