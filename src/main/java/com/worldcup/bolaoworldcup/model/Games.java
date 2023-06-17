package com.worldcup.bolaoworldcup.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Entity
@Table(name="games")
@Data
public class Games  implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
    @JoinColumn(name = "home", referencedColumnName = "id")
	private Team home ;
	@OneToOne
    @JoinColumn(name = "visitor", referencedColumnName = "id")
	private Team visitor ;
	@Column(length=500)
	private String grupo;
	@Column(length=500)
	private String stadium;
	@Column
	private Long match;
	@Column
	private Long round;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Column
	private Long scoreHome;
	@Column
	private Long scoreVisitor;
	@Override
	public String toString() {
		return "Games [date=" + date + ", home=" + home.getName() +","+ home.getId() + ", visitor=" + visitor.getName() + ", group=" + grupo + ", stadium=" + stadium + "]";
	}
	
		
}
