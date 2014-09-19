package be.klusjes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.klusjes.dao.interfaces.UserDao;
import be.klusjes.dao.interfaces.WorkersDao;
import be.klusjes.entities.Authority;
import be.klusjes.entities.JobType;
import be.klusjes.entities.Location;
import be.klusjes.entities.User;
import be.klusjes.entities.Worker;
import be.klusjes.web.UpdateWorkerForm;
import be.klusjes.web.WorkerForm;

@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {

	private UserDao userDao;
	@Autowired
	private JobTypeService jobTypeService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private WorkersDao workersDao;
	@Autowired
	private MailSendingServiceImpl mailSendingService;

	@Autowired
	public WorkerServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}
	
	public void update(Worker worker){
		User user = worker.getUser();
		worker.setUser(user);
		user.setWorker(worker);
		workersDao.update(worker);
	}

	@Override
	public void update(UpdateWorkerForm updateWorkerForm, Worker worker) {
		List<Location> locationsToRemove = new ArrayList<>();
		List<JobType> jobsToRemove = new ArrayList<>();

		// add newly checked jobTypes to worker object
		for (JobType jobType : jobTypeService
				.convertFromListOfStringIds(updateWorkerForm.getJobTypes())) {
			if (!(worker.getJobTypes().contains(jobType))) {
				worker.addJobType(jobType);
			}
		}
		// add newly unchecked jobtypes to jobstoremove-list
		for (JobType jobType : worker.getJobTypes()) {
			if (!(jobTypeService.convertFromListOfStringIds(updateWorkerForm
					.getJobTypes())).contains(jobType)) {
				jobsToRemove.add(jobType);
			}
		}
		// remove jobtypes in jobstoremovelist from worker object
		for (JobType jobType : jobsToRemove) {
			worker.removeJobType(jobType);
		}
		// add newly checked locations to worker object
		for (Location location : locationService
				.convertFromListOfStringIds(updateWorkerForm.getLocations())) {
			if (!(worker.getJobTypes().contains(location))) {
				worker.addLocation(location);
			}
		}
		// add newly unchecked locations to locationsteremovelist
		for (Location location : worker.getLocations()) {
			if (!(jobTypeService.convertFromListOfStringIds(updateWorkerForm
					.getJobTypes())).contains(location)) {
				locationsToRemove.add(location);
			}
		}
		// remove locations in locationstoremovelist from worker object
		for (Location location : locationsToRemove) {
			worker.removeLocation(location);
		}
		// set other Worker properties in User object

		worker.setFirstName(updateWorkerForm.getFirstName());
		worker.setLastName(updateWorkerForm.getLastName());
		worker.setEmail(updateWorkerForm.getEmail());
		worker.setExtraInformation(updateWorkerForm.getExtraInformation());
		worker.setPhoneNumbers(updateWorkerForm.getPhoneNumbers());

		// steps to persist worker object via user object

		User user = worker.getUser();
		worker.setUser(user);
		user.setWorker(worker);
		workersDao.update(worker);
	}

	@Override
	public void create(Worker worker) {
		// TODO Auto-generated method stub

	}

	@Override
	public Worker read(String username) {
		User user = userDao.read(username);
		return user.getWorker();
	}

	@Override
	public List<Worker> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	// zet WorkerForm om naar Persistable userobject
	public User WorkerFormClassToUserClass(WorkerForm workerForm) {
		List<Location> locationList = new ArrayList<>();
		List<JobType> jobTypeList = new ArrayList<>();

		// setting up user login information
		User user = new User();
		user.setEnabled(false);
		user.setUsername(workerForm.getUsername());
		user.setPassword(workerForm.getPassword());
		user.setRandomUUID(java.util.UUID.randomUUID().toString());
		// setting up security authority
		Authority authority = new Authority();
		authority.setAuthority(workerForm.getAuthority());
		// setting workerspecific properties
		Worker worker = new Worker();
		worker.setExtraInformation(workerForm.getExtraInformation());
		worker.setEmail(workerForm.getEmail());
		worker.setFirstName(workerForm.getFirstName());
		worker.setLastName(workerForm.getLastName());
		worker.setPhoneNumbers(workerForm.getPhoneNumbers());
		worker.setJobType(jobTypeService.convertFromListOfStringIds(workerForm
				.getJobTypes()));
		worker.setDateAccount(workerForm.getDateAccount());

		worker.setExtraInformation(workerForm.getExtraInformation());
		// setting worker locations
		for (Location location : locationService
				.convertFromListOfStringIds(workerForm.getLocations())) {
			location.addWorker(worker);
			locationList.add(location);
		}
		worker.setLocations(locationList);
		// setting worker jobtypes
		for (JobType jobType : jobTypeService
				.convertFromListOfStringIds(workerForm.getJobTypes())) {
			jobType.addWorker(worker);
			jobTypeList.add(jobType);
		}
		worker.setJobType(jobTypeList);

		user.setAuthority(authority);
		user.setWorker(worker);

		authority.setUser(user);

		worker.setUser(user);

		return user;
	}

	public User UpdateWorkerFormClassToUserClass(
			UpdateWorkerForm updateWorkerForm, Worker worker) {
		List<Location> locationList = new ArrayList<>();
		List<JobType> jobTypeList = new ArrayList<>();

		// setting workerspecific properties
		User user = worker.getUser();
		worker.setExtraInformation(updateWorkerForm.getExtraInformation());
		worker.setEmail(updateWorkerForm.getEmail());
		worker.setFirstName(updateWorkerForm.getFirstName());
		worker.setLastName(updateWorkerForm.getLastName());
		worker.setPhoneNumbers(updateWorkerForm.getPhoneNumbers());
		worker.setJobType(jobTypeService
				.convertFromListOfStringIds(updateWorkerForm.getJobTypes()));

		worker.setExtraInformation(updateWorkerForm.getExtraInformation());
		// setting worker locations
		for (Location location : locationService
				.convertFromListOfStringIds(updateWorkerForm.getLocations())) {
			location.addWorker(worker);
			locationList.add(location);
		}
		worker.setLocations(locationList);
		// setting worker jobtypes
		for (JobType jobType : jobTypeService
				.convertFromListOfStringIds(updateWorkerForm.getJobTypes())) {
			jobType.addWorker(worker);
			jobTypeList.add(jobType);
		}
		worker.setJobType(jobTypeList);
		user.setWorker(worker);
		worker.setUser(user);

		return user;
	}

	@Override
	public void create(WorkerForm workerForm) {
		User user = this.WorkerFormClassToUserClass(workerForm);
		mailSendingService.sendConfirmationEmail(user);
		userDao.create(user);
	}
	

	@Override
	public List<Worker> findWorkersByJobTypeAndLocation(List<Long> jobTypeId,
			List<Long> locationId) {
		return workersDao.findWorkersByJobTypeAndLocation(jobTypeId, locationId);
	}

	@Override
	public Worker readById(Long id) {
		return workersDao.read(id);
	}

	@Override
	public List<Worker> findCustomersByText(String keyword) {
		return workersDao.findCustomersByText(keyword);
	}

	@Override
	public List<String> findAllWorkerEmails() {
		return workersDao.findAllWorkerEmails();
	}



}
