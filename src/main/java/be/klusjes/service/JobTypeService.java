package be.klusjes.service;

import java.util.List;

import be.klusjes.entities.JobType;

public interface JobTypeService {
	public List<JobType> findAll();
	public JobType read(long id);
	public void create(JobType jobType);
	public List<JobType> convertFromListOfStringIds(List<String>ids);
}
