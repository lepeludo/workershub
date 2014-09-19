package be.klusjes.web;

import java.util.ArrayList;
import java.util.List;

import be.klusjes.constraints.ListOfStringsNotEmptyChecker;

public class LocationJobTypeForm {
	@ListOfStringsNotEmptyChecker
	private List<String> jobTypes = new ArrayList<>();
	@ListOfStringsNotEmptyChecker
	private List<String> locations = new ArrayList<>();

}
