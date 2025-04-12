package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import model.Compromisso;
import utils.JPAUtil;

public class CompromissoDAOImpl implements CompromissoDAO {

	private EntityManager em;
	
	public CompromissoDAOImpl() {
		this.em = JPAUtil.getEntityManager();
		
	}
	
	
	@Override
	public void save(Compromisso compromisso) {
		em.getTransaction().begin();
		em.persist(compromisso);
		em.getTransaction().commit();
		
	}

	@Override
	public Compromisso findById(int id) {
		return em.find(Compromisso.class, id);
	}

	@Override
	public List<Compromisso> findAll() {
		return em
				.createQuery("FROM Compromisso", Compromisso.class)
				.getResultList();
	}

	@Override
	public List<Compromisso> findByContatoId(int contatoId) {
		return em.createQuery("FROM Compromisso c WHERE c.contato.id = :contatoId", Compromisso.class)
				.setParameter("contatoId", contatoId)
				.getResultList();
	}
	
	@Override
	public void update(Compromisso compromisso) {
		em.getTransaction().begin();
		em.merge(compromisso);
		em.getTransaction().commit();
		
	}

	@Override
	public void delete(int id) {
		em.getTransaction().begin();
		Compromisso compromisso = findById(id);
		em.remove(compromisso);
		em.getTransaction().commit();
		
	}


	@Override
	public void deleteAllByContatoId(int contatoId) {
		List<Compromisso> lista = em.createQuery("FROM Compromisso c WHERE c.contato.id = :contatoId", Compromisso.class)
				.setParameter("contatoId", contatoId)
				.getResultList();
		
		for(Compromisso compromisso : lista) {
			em.getTransaction().begin();
			em.remove(compromisso);
			em.getTransaction().commit();
		}
		
		
	}
	

}
