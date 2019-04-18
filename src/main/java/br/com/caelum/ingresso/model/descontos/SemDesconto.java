package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {

	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal valorOriginal) {
		return valorOriginal;
	}

	@Override
	public String getDescricao() {
		return "Inteiro";
	}

}
