package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {

	private List<Sessao> sessoesExistentes;

	public GerenciadorDeSessao(List<Sessao> sessoesExistentes) {
		this.sessoesExistentes = sessoesExistentes;
	}

	public boolean cabe(Sessao sessaoNova) {
		return sessoesExistentes.stream().noneMatch(sessaoExistente -> isConflitante(sessaoExistente, sessaoNova));
		
		// for (Sessao sessaoExistente : sessoesExistentes) {
		// if (isConflitante(sessaoExistente, sessaoNova)) {
		// return false;
		// }
		// }
		// return true;
	}

	private boolean isConflitante(Sessao sessaoExistente, Sessao sessaoNova) {

		LocalDate hoje = LocalDate.now();
		LocalDateTime horarioSessaoExistente = sessaoExistente.getHorario().atDate(hoje);
		LocalDateTime horarioSessaoNova = sessaoNova.getHorario().atDate(hoje);
		boolean terminaAntes = sessaoNova.getHorarioTermino().atDate(hoje).isBefore(horarioSessaoExistente);
		boolean comecaDepois = sessaoExistente.getHorarioTermino().atDate(hoje).isBefore(horarioSessaoNova);
		if (terminaAntes || comecaDepois) {
			return false;
		}
		return true;

	}

}
