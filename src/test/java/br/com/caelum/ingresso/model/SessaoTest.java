package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	@Test
	public void deveRetornarOValorCorretoQuandoPassarUmFilmeESalaComValor() {
		Sala sala = new Sala("Sala Cara", BigDecimal.valueOf(52));
		Filme filme = new Filme("A volta de Sandy & Junior", Duration.ofMinutes(240), "Depre",
				BigDecimal.valueOf(2.75));
		Sessao sessao = new Sessao(LocalTime.of(02, 00), sala, filme);
		Assert.assertEquals(BigDecimal.valueOf(54.75), sessao.getPreco());
	}

}
