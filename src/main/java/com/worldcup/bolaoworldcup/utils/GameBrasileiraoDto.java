package com.worldcup.bolaoworldcup.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameBrasileiraoDto {
		public int id;
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    public LocalDateTime data_realizacao;
	    public String hora_realizacao;
	    public Integer placar_oficial_visitante;
	    public Integer placar_oficial_mandante;
	    public Object placar_penaltis_visitante;
	    public Object placar_penaltis_mandante;
	    public Equipes equipes;
	    public Sede sede;
	    public Transmissao transmissao;
}  	
	

	
		@Data
    	 class Transmissao{
    	    public String label;
    	    public String url;
    	}

			
		