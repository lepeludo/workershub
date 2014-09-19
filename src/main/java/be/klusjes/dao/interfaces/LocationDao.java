package be.klusjes.dao.interfaces;

import java.util.List;

import be.klusjes.entities.Location;

public interface LocationDao {
	public List<Location> findAll();
	public Location read(long id);
	public void create(Location location);
	public Location read(String province);
}
