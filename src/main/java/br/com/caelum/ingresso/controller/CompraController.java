package br.com.caelum.ingresso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.form.CarrinhoForm;

@Controller
public class CompraController {

	@Autowired
	private Carrinho carrinho;

	@Autowired
	private SessaoDao sessaoDao;

	@Autowired
	private LugarDao lugarDao;

	@GetMapping("/compra")
	public ModelAndView checkout() {
		ModelAndView modelAndView = new ModelAndView("compra/pagamento");

		modelAndView.addObject("carrinho", carrinho);

		return modelAndView;
	}

	@PostMapping("/compra/ingressos")
	public ModelAndView mandaParaPagamentos(CarrinhoForm carrinhoForm) {
		ModelAndView modelAndView = new ModelAndView("redirect:/compra");

		// List<Ingresso> ingressos = carrinhoForm.toIngressos(sessaoDao,
		// lugarDao);
		// for(Ingresso ingresso : ingressos) {
		// carrinho.add(ingresso);
		// }

		carrinhoForm.toIngressos(sessaoDao, lugarDao).forEach(carrinho::add);

		return modelAndView;
	}

}
