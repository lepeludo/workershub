package be.klusjes.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import be.klusjes.service.WorkerService;

public class WorkerEmailExistsCheckerValidator implements
		ConstraintValidator<WorkerEmailExistsChecker, String> {
	@Autowired
	WorkerService workerService;

	@Override
	public void initialize(WorkerEmailExistsChecker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		for (String mail : workerService.findAllWorkerEmails()) {
			if (arg0.equalsIgnoreCase(mail)) {
				return false;
			}
		}
		return true;
	}

}
