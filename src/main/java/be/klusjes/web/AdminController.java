package be.klusjes.web;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import be.klusjes.entities.Customer;
import be.klusjes.entities.JobType;
import be.klusjes.entities.Location;
import be.klusjes.entities.Reviews;
import be.klusjes.entities.User;
import be.klusjes.entities.Worker;
import be.klusjes.service.AdministratorService;
import be.klusjes.service.CustomerService;
import be.klusjes.service.JobTypeService;
import be.klusjes.service.LocationService;
import be.klusjes.service.MailSendingServiceImpl;
import be.klusjes.service.ReviewService;
import be.klusjes.service.UserService;
import be.klusjes.service.WorkerService;

@Controller
@RequestMapping("/administrator")
public class AdminController {

	@Autowired
	AdministratorService administratorService;
	@Autowired
	JobTypeService jobTypeService;
	@Autowired
	LocationService locationService;
	@Autowired
	WorkerService workerService;
	@Autowired
	UserService userService;
	@Autowired
	CustomerService customerService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	MailSendingServiceImpl mailSendingService;
	
	
	private ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();
	private List<Location> locations;
	private List<JobType> jobTypes;

	@RequestMapping(method = RequestMethod.GET)
	public String showIndex() {
		return "administrator/administratorindex";
	}

	// administrator administrators
	// create new administrator
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateCustomer(Model model,
			@ModelAttribute AdministratorForm administratorForm) {
		return "administrator/create";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model,
			@Valid AdministratorForm administratorForm, BindingResult result) {
		if (result.hasErrors()) {
			return "administrator/create";
		}
		administratorForm.setPassword(shaPasswordEncoder.encodePassword(
				administratorForm.getPassword(),
				administratorForm.getUsername()));
		administratorService.create(administratorForm);
		model.addAttribute("administrator",
				administratorService.read(administratorForm.getUsername()));
		return "administrator/created";
	}

	// administrator workers
	// load search windows
	@RequestMapping(value = "/searchworkerslocationtype", method = RequestMethod.GET)
	public String showStartSearchWorkers(Model model,
			@ModelAttribute("workersForm") WorkersForm workersForm) {
		model.addAttribute("jobTypes", jobTypeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		return "administrator/searchworkerslocationtype";
	}

	// search workers by location and type of job
	@RequestMapping(value = "/searchworkerslocationtype", method = RequestMethod.POST)
	public String doSearchWorkers(Model model,
			@Valid @ModelAttribute("workersForm") WorkersForm workersForm,
			BindingResult result) {
		model.addAttribute("jobTypes", jobTypeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		if (result.hasErrors()) {
			return "administrator/searchworkerslocationtype";
		}
		List<Worker> relevantWorkers = workerService
				.findWorkersByJobTypeAndLocation(workersForm.getJobTypesLong(),
						workersForm.getLocationLong());
		model.addAttribute("workers", relevantWorkers);
		for (Worker worker : relevantWorkers) {
			System.out.println(worker.getEmail());
		}
		return "administrator/searchworkerslocationtype";
	}

	// search workers by keywords
	@RequestMapping(value = "/searchworkerslocationtype", method = RequestMethod.GET, params = "keyword")
	public String doSearchWorkersByName(Model model,
			@RequestParam String keyword,
			@ModelAttribute("workersForm") WorkersForm workersForm) {
		List<Worker> workers = workerService.findCustomersByText(keyword);
		model.addAttribute("jobTypes", jobTypeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("workers", workers);
		for (Worker worker : workers) {
			System.out.println(worker.getEmail());
		}
		return "administrator/searchworkerslocationtype";

	}

	// enable or disable workers
	@RequestMapping(value = "/enabledisableworkers", method = RequestMethod.POST, params = "allWorkerIds")
	public String showWorkers(Model model,
			@RequestParam(required = false) Long[] workerIds,
			@RequestParam Long[] allWorkerIds) {
		if (workerIds == null) {
			for (Long idAll : allWorkerIds) {
				User user = workerService.readById(idAll).getUser();
				user.setEnabled(false);
				userService.update(user);
			}
			return "redirect:/administrator/";
		}
		List<Long> workerIdList = Arrays.asList(workerIds);
		List<Long> allWorkerIdList = Arrays.asList(allWorkerIds);

		for (Long idAll : allWorkerIdList) {
			if (!workerIdList.contains(idAll)) {
				User user = workerService.readById(idAll).getUser();
				user.setEnabled(false);
				userService.update(user);
			} else {
				Worker worker = workerService.readById(idAll);
				if (worker.getUser().isEnabled() == false) {
					User user = worker.getUser();
					user.setEnabled(true);
					userService.update(user);
				}
			}
		}
		return "administrator/administratorindex";
	}

	// administrator customer
	// load search windows
	@RequestMapping(value = "/searchcustomers", method = RequestMethod.GET)
	public String showStartSearchCustomers(Model model) {
		return "administrator/searchcustomers";
	}

	// do customersearch
	@RequestMapping(value = "/searchcustomers", method = RequestMethod.GET, params = "keyword")
	public String doSearchCustomers(Model model, @RequestParam String keyword) {
		List<Customer> customers = customerService.findCustomersByText(keyword);
		model.addAttribute("customers", customers);
		return "administrator/searchcustomers";
	}

	// enable or disable customers
	@RequestMapping(value = "/enablecustomers", method = RequestMethod.POST, params = "allCustomerIds")
	public String alterCustomers(Model model,
			@RequestParam(required = false) Long[] customerIds,
			@RequestParam Long[] allCustomerIds) {
		if (customerIds == null) {
			for (Long idAll : allCustomerIds) {
				User user = customerService.readById(idAll).getUser();
				user.setEnabled(false);
				userService.update(user);
			}
			return "redirect:searchcustomers";
		}
		List<Long> customerIdList = Arrays.asList(customerIds);
		List<Long> allCustomerIdList = Arrays.asList(allCustomerIds);

		for (Long idAll : allCustomerIdList) {
			if (!customerIdList.contains(idAll)) {
				User user = customerService.readById(idAll).getUser();
				user.setEnabled(false);
				userService.update(user);
			} else {
				Customer customer = customerService.readById(idAll);
				if (customer.getUser().isEnabled() == false) {
					User user = customer.getUser();
					user.setEnabled(true);
					userService.update(user);
				}
			}
		}
		return "redirect:searchcustomers";
	}

	// adminside review handling

	@RequestMapping(value = "/reviews", method = RequestMethod.GET)
	public String showReviews(Model model) {
		List<Reviews> reviews = reviewService.findUnreadReportedReviews();
		model.addAttribute("reviews", reviews);
		return "administrator/reviews";
	}

	@RequestMapping(value = "/reviews", method = RequestMethod.POST)
	public String reportReviews(Model model,
			@RequestParam(required = false) Long[] deletes,
			@RequestParam(required = false) Long[] readByAdmins) {

		if (deletes == null && readByAdmins == null) {
			return "redirect:reviews";
		}
		if (deletes != null) {
			List<Long> deletesList = Arrays.asList(deletes);
			for (Long idReview : deletesList) {
				Reviews review = reviewService.read(idReview);
				Customer customer = review.getCustomer();
				long addOne = customer.getNumberOfDeleteReviews() + 1;
				customer.setNumberOfDeleteReviews(addOne);
				customerService.update(customer);
				review.setWorker(null);
				review.setCustomer(null);
				reviewService.update(review);
				reviewService.deleteReview(idReview);
			}
		}
		if (readByAdmins != null) {
			List<Long> readByAdminList = Arrays.asList(readByAdmins);
			for (Long idReview : readByAdminList) {
				Reviews review = reviewService.read(idReview);
				review.setReadByAdmin(true);
				reviewService.update(review);
			}
		}

		return "redirect:reviews";
	}

	// add locations and jobtypes

	@RequestMapping(value = "/addlocationsandjobtypes", method = RequestMethod.GET)
	public String showLocationsAndJobTypes(Model model) {
		locations = locationService.findAll();
		jobTypes = jobTypeService.findAll();
		model.addAttribute("locations", locations);
		model.addAttribute("jobTypes", jobTypes);
		return "administrator/addlocationsandjobtypes";
	}

	@RequestMapping(value = "/addlocationsandjobtypes", method = RequestMethod.POST, params = "jobType")
	public String addNewJobType(
			Model model,
			@RequestParam(required = true) @NotBlank @Length(min = 4) String jobTypeDescription) {

		JobType newJobType = new JobType();
		newJobType.setJobDescription(jobTypeDescription);
		jobTypeService.create(newJobType);
		jobTypes = jobTypeService.findAll();
		model.addAttribute("jobTypes", jobTypes);
		model.addAttribute("locations", locations);
		return "redirect:addlocationsandjobtypes";
	}

	@RequestMapping(value = "/addlocationsandjobtypes", method = RequestMethod.POST, params = "location")
	public String addLocation(
			Model model,
			@RequestParam(required = true) @NotBlank @Length(min = 4) String province) {

		Location newLocation = new Location();
		newLocation.setCountry("Belgium");
		newLocation.setProvince(province);
		locationService.create(newLocation);
		locations = locationService.findAll();
		model.addAttribute("jobTypes", jobTypes);
		model.addAttribute("locations", locations);
		return "redirect:addlocationsandjobtypes";
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public String emailToUser(Model model,
			@RequestParam String email) {
		model.addAttribute("email", email);
		return "administrator/email";
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String doEmailToUser(Model model,
			@RequestParam String email, @RequestParam String message) {
		mailSendingService.sendMail("workerforum", email, "Important message form WorkerPoint", message);
		return "redirect:/administrator";
		
	}
}
