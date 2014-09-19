package be.klusjes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jobtypes")
public class JobType implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String jobDescription;
	@ManyToMany(mappedBy = "jobTypes", fetch = FetchType.LAZY)
	private List<Worker> workers = new ArrayList<>();

	public JobType() {
	}

	public void addWorker(Worker worker) {
		if (workers.contains(worker)) {
			return;
		}
		workers.add(worker);
		worker.addJobType(this);
	}
	
	public void removeWorker(Worker worker) {
		if (!workers.contains(worker))
			return;
		workers.remove(worker);
		worker.removeJobType(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public List<Worker> getWorkers() {
		return Collections.unmodifiableList(workers);
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	@Override
	public int hashCode() {
		int result;
		result = getWorkers().hashCode();
		result = (int) (29*result + getId());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof JobType)){
			return false;
		}
		JobType temp = (JobType)obj;
		if(temp.getJobDescription().equals(this.getJobDescription())){
			return true;
		}

		return false;
	}

}
