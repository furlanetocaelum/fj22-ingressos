package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {
	
	private Filme filme;
	private Sala sala;
	private Sessao sessaoDasDez;
	
	@After
	public void tearDown() {
		System.out.println("Executando @After");
	}
	
	@Before
	public void setUp() {
		System.out.println("Executando @Before");
		filme = new Filme("Titanic", Duration.ofMinutes(97), "Drama");
		sala = new Sala("Sala Dahora");
		sessaoDasDez =  new Sessao(LocalTime.parse("10:00:00"), sala, filme);
	}
	
	@Test
	public void deveAdicionarNovaSessaoQuandoNaoPossuiSessoesExistentes() {
		System.out.println("Executando 1");
		List<Sessao> sessoesExistentes = new ArrayList<>();
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesExistentes);
		
		
		Sessao sessaoNova = new Sessao(LocalTime.of(10, 20), sala, filme);
		
		boolean resultado = gerenciador.cabe(sessaoNova);
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void naoDeveAdicionarNovaSessaoNoMeioDeUmaSessaoExistentes() {
		System.out.println("Executando 2");
		List<Sessao> sessoesExistentes = new ArrayList<>();
		
		sessoesExistentes.add(sessaoDasDez);
		
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesExistentes);
		
		Sessao sessaoNova = new Sessao(LocalTime.parse("11:00:00"), sala, filme);
		boolean resultado = gerenciador.cabe(sessaoNova);
		Assert.assertFalse(resultado);
	}
	
	@Test
	public void deveAdicionarNovaSessaoDepoisDaSessaoExistente() {
		System.out.println("Executando 3");
		
	}

}
