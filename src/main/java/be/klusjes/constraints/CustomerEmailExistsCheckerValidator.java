package be.klusjes.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import be.klusjes.service.CustomerService;

public class CustomerEmailExistsCheckerValidator implements
		ConstraintValidator<CustomerEmailExistsChecker, String> {

	@Autowired
	CustomerService customerService;

	@Override
	public void initialize(CustomerEmailExistsChecker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		for (String mail : customerService.findAllCustomerEmails()) {
			if (arg0.equalsIgnoreCase(mail)) {
				return false;
			}
		}
		return true;
	}

}
