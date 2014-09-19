package be.klusjes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.klusjes.dao.interfaces.JobTypesDao;
import be.klusjes.entities.JobType;

@Service
@Transactional
public class JobTypeServiceImpl implements JobTypeService {

	private JobTypesDao jobTypesDao;

	@Autowired
	public JobTypeServiceImpl(JobTypesDao jobTypesDao) {
		this.jobTypesDao = jobTypesDao;
	}

	@Override
	public List<JobType> findAll() {
		return jobTypesDao.findAll();
	}

	@Override
	public JobType read(long id) {
		return jobTypesDao.read(id);
	}

	@Override
	public void create(JobType jobType) {
		jobTypesDao.create(jobType);

	}

	/*method to transform List of id's in String-format from the WorkerForm class,
	 as reveived from the worker/create jsp in the WorkerController
	 */
	@Override
	public List<JobType> convertFromListOfStringIds(List<String> ids) {
		List<JobType> jobTypes = new ArrayList<>();
		for (String idString : ids) {
			Long idLong = Long.parseLong(idString);
			jobTypes.add(jobTypesDao.read(idLong));
		}
		return jobTypes;
	}
}
