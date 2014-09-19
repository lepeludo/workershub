package be.klusjes.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import be.klusjes.service.UserService;

public class UsernameCheckerValidator implements ConstraintValidator<UsernameExistsChecker, String> {

	@Autowired
	UserService userService;
	
	@Override
	public void initialize(UsernameExistsChecker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		for(String username:userService.findAllUsernames()){
			if (arg0.equalsIgnoreCase(username)){
				return false;
			}
		}
		return true;
	}

}
