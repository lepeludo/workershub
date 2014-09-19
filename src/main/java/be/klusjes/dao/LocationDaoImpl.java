package be.klusjes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import be.klusjes.dao.interfaces.LocationDao;
import be.klusjes.entities.Location;

@Repository
public class LocationDaoImpl implements LocationDao {
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Location> findAll() {
		TypedQuery<Location> query = entityManager.createNamedQuery(
				"findAllLocations", Location.class);
		return query.getResultList();
	}

	@Override
	public Location read(long id) {
		return entityManager.find(Location.class, id);

	}

	@Override
	public void create(Location location) {
		try {
			entityManager.persist(location);
		} catch (RuntimeException ex) {
			entityManager.clear();
			throw ex;
		}
	}

	@Override
	public Location read(String province) {
		TypedQuery<Location> query = entityManager.createNamedQuery(
				"findLocationByProvince", Location.class);
		return query.getSingleResult();
	}

}
