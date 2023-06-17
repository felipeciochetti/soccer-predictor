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
public enum StatusInvite {

	ACTIVE("ACTIVE",0L),
	CANCEL("CANCEL",1L),
	ACCEPTED("ACCEPTED",2L),
	REJECTED("REJECTED",3L);
	
	
    private String label;
    private Long valor;

    private  StatusInvite(String label,Long valor) {
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
