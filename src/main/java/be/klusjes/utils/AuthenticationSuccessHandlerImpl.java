package be.klusjes.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import be.klusjes.entities.Administrator;
import be.klusjes.entities.Customer;
import be.klusjes.entities.Worker;
import be.klusjes.service.AdministratorService;
import be.klusjes.service.CustomerService;
import be.klusjes.service.WorkerService;

public class AuthenticationSuccessHandlerImpl implements
		AuthenticationSuccessHandler {
	@Autowired
	CustomerService customerService;
	@Autowired
	WorkerService workerService;
	@Autowired
	AdministratorService administratorService;
	Calendar cal = Calendar.getInstance();
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// make list of authorities in string format
			List <String> authorityStrings = new ArrayList<>();
			
			for(GrantedAuthority temp:auth.getAuthorities()){
				authorityStrings.add(temp.getAuthority());
			}
			// if authority == customer
			if (authorityStrings.contains("customer")){
				Customer customer = customerService.read(auth.getName());				
				customer.setDateLastLogin(cal.getTime());
				customer.setNumberOfLogins(customer.getNumberOfLogins()+1);
				customerService.update(customer);
				response.sendRedirect(request.getContextPath()+"/customer");
			}else if(authorityStrings.contains("worker")){
				Worker worker = workerService.read(auth.getName());
				worker.setDateLastLogin(cal.getTime());
				worker.setNumberOfLogins(worker.getNumberOfLogins()+1);
				workerService.update(worker);
				response.sendRedirect(request.getContextPath()+"/worker");
			}else if(authorityStrings.contains("administrator")){
				Administrator administrator = administratorService.read(auth.getName());
				administrator.setDateLastLogin(cal.getTime());
				administrator.setNumberOfLogins(administrator.getNumberOfLogins()+1);
				administratorService.update(administrator);
				response.sendRedirect(request.getContextPath()+"/administrator");
			}
	}

}
