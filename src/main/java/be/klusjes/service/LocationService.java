package be.klusjes.service;

import java.util.List;

import be.klusjes.entities.Location;

public interface LocationService {
	
	public List<Location> findAll();
	public Location read(long id);
	public void create(Location location);
	public Location read(String province);
	List<Location> convertFromListOfStringIds(List<String> ids);
}
