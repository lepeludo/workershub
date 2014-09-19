package be.klusjes.service;

import be.klusjes.entities.Administrator;
import be.klusjes.web.AdministratorForm;

public interface AdministratorService {
	public void create(AdministratorForm administratorForm);

	public Administrator read(String username);

	public void update(Administrator administrator);
}
