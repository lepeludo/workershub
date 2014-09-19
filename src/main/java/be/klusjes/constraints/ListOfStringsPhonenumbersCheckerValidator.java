package be.klusjes.constraints;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ListOfStringsPhonenumbersCheckerValidator implements
		ConstraintValidator<ListOfStringsPhonenumbersChecker, List<String>> {

	@Override
	public void initialize(ListOfStringsPhonenumbersChecker b) {

	}

	@Override
	public boolean isValid(List<String> arg0, ConstraintValidatorContext arg1) {
		int counter = 0;
		for (String phonenumber : arg0) {
			if (phonenumber.length() < 9 || phonenumber.length() > 25) {
				return false;
			} else if (phonenumber.charAt(0) != '0') {
				return false;
			}
			for (int i = 0; i < phonenumber.length(); i++) {
				if (phonenumber.substring(i, i+1).matches("[0-9]")) {
					++counter;
				}
			}
		}
		if (counter >= 9) {
			return true;
		}
		return false;
	}
}