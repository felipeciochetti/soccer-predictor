package com.worldcup.bolaoworldcup.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.worldcup.bolaoworldcup.model.enuns.Bets;

import lombok.Data;

@Entity
@Table(name="bet_user_game_points")
@Data
public  class BetUserGamePoints  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	BetUserGameKey id;

    @ManyToOne
    @MapsId("userId")
    Usuarios user;

    @ManyToOne
    @MapsId("gameId")
    Games game;

    @Column
	private Long points;
    
    @Enumerated(EnumType.STRING)
	private Bets betType;
	
	@Column
	@Temporal(TemporalType.DATE)	
	private Date date;
    
    
    
}