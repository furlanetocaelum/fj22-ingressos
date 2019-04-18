package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeDesconto;


public class DescontoTest {
	
	@Test
	public void deveRetornarMetadeDoValorDaSessaoQuandoODescontoForDeEstudante() {
		Lugar lugar = new Lugar("A", 1);
		Sala sala = new Sala("Sala 4D", new BigDecimal("15.00"));
		Filme filme = new  Filme("Titanic", Duration.ofMinutes(120), "Drama", new BigDecimal("25.00"));
		Sessao sessao = new Sessao(LocalTime.of(10, 00), sala, filme);
		Ingresso ingresso = new Ingresso(sessao, TipoDeDesconto.ESTUDANTE, lugar);
		
		BigDecimal precoEsperadoDoIngresso = new BigDecimal("20.00");
		Assert.assertEquals(precoEsperadoDoIngresso, ingresso.getPreco());
	}

}
