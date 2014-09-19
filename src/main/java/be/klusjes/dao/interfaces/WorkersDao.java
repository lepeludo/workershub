package be.klusjes.dao.interfaces;

import java.util.List;

import be.klusjes.entities.Worker;

public interface WorkersDao {

	void update(Worker worker);

	List<Worker> findWorkersByJobTypeAndLocation(List<Long> jobTypeId,
			List<Long> locationId);

	Worker read(Long id);

	List<Worker> findCustomersByText(String keyword);
	
	List<String> findAllWorkerEmails();

}
