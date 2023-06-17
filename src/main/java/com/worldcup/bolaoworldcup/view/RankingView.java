package com.worldcup.bolaoworldcup.view;

import java.util.List;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RankingView {

	private Long id;
	private String league;
	List<UserPointsPlaceView> listUser;

}
