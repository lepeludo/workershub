package be.klusjes.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String country;
	private String province;
	@ManyToMany(mappedBy = "locations", fetch=FetchType.LAZY)
	private List<Worker> workers;

	public Location() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<Worker> getWorkers() {
		return Collections.unmodifiableList(workers);
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	public void addWorker(Worker worker) {
		if (workers.contains(worker)) {
			return;
		}
		workers.add(worker);
		worker.addLocation(this);
	}

	public void removeWorker(Worker worker) {
		if (!workers.contains(worker))
			return;
		// remove the follower
		workers.remove(worker);
		// remove myself from the follower
		worker.removeLocation(this);

	}

	@Override
	public int hashCode() {
		return getCountry().hashCode()+getProvince().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Location)) {
			return false;
		}
		Location tempLocation = (Location) obj;
		if ((tempLocation.getProvince().hashCode() + tempLocation.getCountry()
				.hashCode()) == (this.getProvince().hashCode() + this
				.getCountry().hashCode())) {
			return true;
		}
		return false;

	}

}
