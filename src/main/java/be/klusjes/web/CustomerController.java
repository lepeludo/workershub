package be.klusjes.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import be.klusjes.entities.Customer;
import be.klusjes.entities.MessageThread;
import be.klusjes.entities.Reviews;
import be.klusjes.service.CustomerService;
import be.klusjes.service.MailSendingServiceImpl;
import be.klusjes.service.MessageThreadService;
import be.klusjes.service.ReviewService;
import be.klusjes.utils.MessageUtil;

@Controller
@RequestMapping("/customer")
@SessionAttributes("unreadMessages")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	MessageThreadService messageThreadService;
	@Autowired 
	MailSendingServiceImpl mailSendingServiceImpl;

	private ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();


//set unread messages as sessionvariable
	public void setMessageSessionParam(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Customer customer = customerService.read(auth.getName());
		List<MessageThread> messageThreads = messageThreadService
				.readByCustomerId(customer.getId());
		MessageUtil messageUtil = new MessageUtil();
		messageUtil.setMessageSessionParam(model, messageThreads);	
//mailSendingServiceImpl.sendMail("bramdeveirman@gmail.com", "bramdeveirman@gmail.com", "test", "ik ben een test");
		
	}
// indexpage
	@RequestMapping(method = RequestMethod.GET)
	public String showIndex(Model model) {
		this.setMessageSessionParam(model);	
		return "customer/customerindex";
	}
//create a new customer
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateCustomer(Model model,
			@ModelAttribute CustomerForm customerForm) {
		return "customer/create";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Valid CustomerForm customerForm,
			BindingResult result) {
		if (result.hasErrors()) {
			return "customer/create";
		}
		customerForm.setPassword(shaPasswordEncoder.encodePassword(customerForm.getPassword(), customerForm.getUsername()));
		customerService.create(customerForm);
		model.addAttribute("customer",
				customerService.read(customerForm.getUsername()));
		
		return "customer/created";
	}
//update an existing customer
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String showUpdateCustomer(
			Model model,
			@ModelAttribute("updateCustomerForm") UpdateCustomerForm updateCustomerForm) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Customer customer = customerService.read(auth.getName());
		updateCustomerForm.addExistingCustomer(customer);
		return "customer/update";
	}

	@RequestMapping(value = "/doupdate", method = RequestMethod.POST)
	public String doUpdate(
			Model model,
			@Valid @ModelAttribute("updateCustomerForm") UpdateCustomerForm updateCustomerForm,
			BindingResult result) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Customer customer = customerService.read(auth.getName());
		if (result.hasErrors()) {
			return "worker/update";
		}

		customerService.update(updateCustomerForm, customer);
		return "redirect:profile";
	}
//customerprofile
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String showProfile(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Customer customer = customerService.read(auth.getName());
		model.addAttribute("customer", customer);
		return "customer/profile";
	}
//show reviews written by customer
	@RequestMapping(value = "/reviews", method = RequestMethod.GET)
	public String showReviews(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Customer customer = customerService.read(auth.getName());
		List<Reviews> reviews = reviewService
				.readByCustomerId(customer.getId());
		model.addAttribute("reviews", reviews);
		return "customer/reviews";
	}
	


}
