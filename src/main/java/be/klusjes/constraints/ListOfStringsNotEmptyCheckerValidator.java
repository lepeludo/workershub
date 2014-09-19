package be.klusjes.constraints;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ListOfStringsNotEmptyCheckerValidator implements
		ConstraintValidator<ListOfStringsNotEmptyChecker, List<String>> {

	@Override
	public void initialize(ListOfStringsNotEmptyChecker b) {

	}

	@Override
	public boolean isValid(List<String> arg0, ConstraintValidatorContext arg1) {
		try {
			if (arg0.isEmpty()) {
				return false;
			}
			return true;

		} catch (NullPointerException ex) {
			return false;
		}
	}
}