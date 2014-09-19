package be.klusjes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import be.klusjes.dao.interfaces.UserDao;
import be.klusjes.entities.Customer;
import be.klusjes.entities.User;

@Repository
public class UserDaoImpl implements UserDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(User user) {
		try {
			entityManager.persist(user);
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}
	}

	@Override
	public User read(String username) {
		return entityManager.find(User.class, username);
	}

	@Override
	public void update(User user) {
		try {
			entityManager.merge(user);
			entityManager.flush();
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}
	}

	@Override
	public void delete(long id) {
		Customer customer = entityManager.find(Customer.class, id);
		if (customer != null) {
			entityManager.remove(customer);
			entityManager.flush();
		}
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = entityManager.createNamedQuery(
				"findAllCustomers", User.class);
		return query.getResultList();
	}

	@Override
	public List<String> findAllUsernames() {
		TypedQuery<String> query = entityManager.createNamedQuery(
				"findAllUsernames", String.class);
		return query.getResultList();
	}

	@Override
	public User readByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
