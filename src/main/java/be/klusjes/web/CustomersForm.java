package be.klusjes.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.klusjes.constraints.ListOfStringsNotEmptyChecker;

public class CustomersForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@ListOfStringsNotEmptyChecker
	private List<String> locations = new ArrayList<>();
	
	
	public CustomersForm() {
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
	
	public List<Long>getLocationLong(){
		List<Long>temp = new ArrayList<>();
		for (String stringId:locations){
			temp.add(Long.parseLong(stringId));
		}
		return temp;
	}

}
