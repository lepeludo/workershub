package be.klusjes.dao.interfaces;

import java.util.List;

import be.klusjes.entities.JobType;

public interface JobTypesDao {
	public List<JobType> findAll();
	public JobType read(long id);
	public void create(JobType jobType);
	
}
