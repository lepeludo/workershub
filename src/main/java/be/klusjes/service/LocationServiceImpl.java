package be.klusjes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.klusjes.dao.interfaces.LocationDao;
import be.klusjes.entities.Location;

@Transactional
@Service
public class LocationServiceImpl implements LocationService {

	private final LocationDao locationDao;

	@Autowired
	public LocationServiceImpl(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	@Override
	public List<Location> findAll() {
		return locationDao.findAll();
	}

	@Override
	public Location read(long id) {
		return locationDao.read(id);
	}

	@Override
	public void create(Location location) {
		locationDao.create(location);
	}

	@Override
	public Location read(String province) {
		return locationDao.read(province);
	}
	
	/*method to transform List of id's in String-format from the WorkerForm class,
	 as reveived from the worker/create jsp in the WorkerController
	 */
	@Override
	public List<Location> convertFromListOfStringIds(List<String> ids) {
		List<Location> locations = new ArrayList<>();
		for (String idString : ids) {
			Long idLong = Long.parseLong(idString);
			locations.add(locationDao.read(idLong));
		}
		return locations;
	}

}
