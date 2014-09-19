package be.klusjes.service;

import java.util.List;

import be.klusjes.entities.Worker;
import be.klusjes.web.UpdateWorkerForm;
import be.klusjes.web.WorkerForm;

public interface WorkerService {
	void delete(long id);


	void create(Worker worker);

	Worker read(String username);

	List<Worker> findAll();

	void create(WorkerForm workerForm);

	void update(UpdateWorkerForm updateWorkerForm, Worker worker);
	
	public void update(Worker worker);
	
	 List<Worker> findWorkersByJobTypeAndLocation(List <Long>jobTypeId, List<Long> locationId);

	Worker readById(Long id);


	List<Worker> findCustomersByText(String keyword);
	
	List<String> findAllWorkerEmails();
}
