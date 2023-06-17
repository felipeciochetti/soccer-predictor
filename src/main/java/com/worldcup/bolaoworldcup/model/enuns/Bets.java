/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldcup.bolaoworldcup.model.enuns;

import lombok.Data;

/**
 *
 */
public enum Bets {

	PLACAR_CHEIO("PLACAR CHEIO",25L),TIME_VENCEDOR_GOLS("TIME VENCEDOR GOLS",18L),TIME_VENCEDOR_SEM_GOLS("TIME VENCEDOR SEM GOLS" , 13L),GOLS_PERDEDOR("GOLS PERDEDOR",10L),EMPATE("EMPATE",13L),EMPATE_GARANTIDO("EMPATE GARANTIDO",5L),
	GOLS_ANY_TEAM("GOLS ALGUM TIME" , 5L),EMPTY("VAZIO",0L);
    
    private String label;
    private Long valor;

    private  Bets(String label,Long valor) {
        this.label = label;
        this.valor = valor;
    }

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}


	
}
