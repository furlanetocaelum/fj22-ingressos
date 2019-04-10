package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

@Repository
public class SessaoDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Sessao sessao) {
		em.persist(sessao);
	}

	public List<Sessao> buscaSessoesPorSala(Sala sala) {
		Query query = em.createQuery("select s from Sessao s where s.sala = :sala", Sessao.class);
		query.setParameter("sala", sala);

		return query.getResultList();
	}
}
