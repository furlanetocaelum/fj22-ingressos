package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.descontos.Desconto;
import br.com.caelum.ingresso.model.descontos.DescontoParaEstudantes;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public enum TipoDeDesconto {

	INTEIRA(new SemDesconto()), 
	ESTUDANTE(new DescontoParaEstudantes());

	private Desconto desconto;

	TipoDeDesconto(Desconto desconto) {
		this.desconto = desconto;
	}

	public Desconto getDesconto() {
		return desconto;
	}

	public BigDecimal aplicaDesconto(BigDecimal valor) {
		return this.desconto.aplicarDescontoSobre(valor);
	}

	public String getDescricao() {
		return this.desconto.getDescricao();
	}
}
