package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

@Controller
public class SessaoController {

	@Autowired
	private FilmeDao filmeDao;

	@Autowired
	private SalaDao salaDao;

	@Autowired
	private SessaoDao sessaoDao;

	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");

		form.setSalaId(salaId);

		modelAndView.addObject("sala", salaDao.findOne(salaId));
		modelAndView.addObject("filmes", filmeDao.findAll());
		modelAndView.addObject("form", form);

		return modelAndView;
	}

	@Transactional
	@PostMapping("/admin/sessao")
	public ModelAndView save(@Valid SessaoForm form, BindingResult result) {

		if (result.hasErrors()) {
			return form(form.getSalaId(), form);
		}

		Sessao sessao = form.toSessao(filmeDao, salaDao);

		List<Sessao> sessoesExistentes = sessaoDao.buscaSessoesPorSala(sessao.getSala());
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesExistentes);

		boolean cabe = gerenciador.cabe(sessao);
		System.out.println("cabe: " + cabe + "\n\n");
		if (cabe) {
			sessaoDao.save(sessao);
			return new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");
		}

		return form(form.getSalaId(), form);
	}
}
