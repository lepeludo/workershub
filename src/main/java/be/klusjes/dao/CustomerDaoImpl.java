package be.klusjes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import be.klusjes.dao.interfaces.CustomerDao;
import be.klusjes.entities.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void update(Customer customer) {
		try {
			entityManager.merge(customer);
			entityManager.flush();
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}

	}

	@Override
	public Customer read(Long id) {
		return entityManager.find(Customer.class, id);
	}

	@Override
	public List<Customer> findCustomersByText(String keyword) {
		TypedQuery<Customer> query = entityManager.createNamedQuery(
				"findCustomersByText", Customer.class);
		query.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
		return query.getResultList();
	}

	@Override
	public List<String> findAllCustomerEmails() {
		TypedQuery<String> query = entityManager.createNamedQuery(
				"findAllCustomerEmails", String.class);
		return query.getResultList();
	}

}
