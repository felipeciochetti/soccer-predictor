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
@Table(name="league_classificacao")
@Data
public  class LeagueClassificacao  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	LeagueClassificaoKey id;

    @ManyToOne
    @MapsId("userId")
    Usuarios user;

    @ManyToOne
    @MapsId("leagueId")
    League league;

    @Column
	private Long points;
	@Column
	private Long place;
	
    
    
    
}