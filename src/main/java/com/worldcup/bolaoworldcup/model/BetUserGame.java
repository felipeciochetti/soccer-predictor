package com.worldcup.bolaoworldcup.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="bet_user_game")
@Data
public  class BetUserGame  implements Serializable{

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
	private Long scoreHome;
	@Column
	private Long scoreVisitor;
	
	@Column
	@Temporal(TemporalType.DATE)	
	private Date date;
    
    
    
}