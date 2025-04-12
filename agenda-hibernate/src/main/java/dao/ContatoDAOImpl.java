package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import model.Contato;
import utils.JPAUtil;

public class ContatoDAOImpl implements ContatoDAO {

	private EntityManager em = JPAUtil.getEntityManager();
	
	
	
	@Override
	public void save(Contato contato) {
		em.getTransaction().begin();
		em.persist(contato);
		em.getTransaction().commit();
		
	}

	@Override
	public Contato findById(int id) {
		return em.find(Contato.class, id);
	}

	@Override
	public List<Contato> findAll() {
		return em
				.createQuery("FROM Contato c ORDER BY c.id ASC", Contato.class)
				.getResultList();
	}

	@Override
	public void update(Contato contato) {
		em.getTransaction().begin();
		em.merge(contato);
		em.getTransaction().commit();
		
	}

	@Override
	public void delete(int id) {
		em.getTransaction().begin();
		Contato contato = findById(id); 
		em.remove(contato);
		em.getTransaction().commit();
		
	}
	
	/*
	 @Override
	 public void delete(Contato contato) {
	 em.getTransaction().begin();
	 em.remove(contato);
	 em.getTransaction().commit();
	 }
	 */

}
