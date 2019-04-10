package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {

	private Integer id;

	@NotNull(message = "Preenche a sala, não pode ser vazia")
	private Integer salaId;

	@NotNull(message = "Preenche o horário, não pode ser vazia")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horario;

	@NotNull(message = "Selecione o filme, não pode ser vazio")
	private Integer filmeId;

	public Sessao toSessao(FilmeDao filmeDao, SalaDao salaDao) {
		Filme filme = filmeDao.findOne(filmeId);
		Sala sala = salaDao.findOne(salaId);
		Sessao sessao = new Sessao(horario, sala, filme);
		sessao.setId(id);

		return sessao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalaId() {
		return salaId;
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Integer getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}

}
