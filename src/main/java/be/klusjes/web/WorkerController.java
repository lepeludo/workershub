package be.klusjes.web;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import be.klusjes.entities.Customer;
import be.klusjes.entities.JobType;
import be.klusjes.entities.Location;
import be.klusjes.entities.MessageThread;
import be.klusjes.entities.Reviews;
import be.klusjes.entities.Worker;
import be.klusjes.service.CustomerService;
import be.klusjes.service.JobTypeService;
import be.klusjes.service.LocationService;
import be.klusjes.service.MessageThreadService;
import be.klusjes.service.ReviewService;
import be.klusjes.service.WorkerService;
import be.klusjes.utils.MessageUtil;

@Controller
@RequestMapping("/worker")
@SessionAttributes("unreadMessages")
public class WorkerController {

	@Autowired
	WorkerService workerService;
	@Autowired
	JobTypeService jobTypeService;
	@Autowired
	LocationService locationService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	CustomerService customerService;
	@Autowired
	MessageThreadService messageThreadService;
	
	private ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();

	private List<JobType> jobTypes = new ArrayList<>();
	private List<Location> locations = new ArrayList<>();

	// util
	public void setMessageSessionParam(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Worker worker = workerService.read(auth.getName());
		List<MessageThread> messageThreads = messageThreadService
				.readByWorkerId(worker.getId());
		MessageUtil messageUtil = new MessageUtil();
		messageUtil.setMessageSessionParam(model, messageThreads);
	}

	// indexpagina

	@RequestMapping(method = RequestMethod.GET)
	public String showIndex(Model model) {

		this.setMessageSessionParam(model);
		return "worker/workerindex";
	}

	// create a new worker

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreate(Model model,
			@ModelAttribute("workerForm") WorkerForm workerForm) {
		jobTypes = jobTypeService.findAll();
		locations = locationService.findAll();
		model.addAttribute("locations", locations);
		model.addAttribute("jobTypes", jobTypes);
		return "worker/create";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST, params = "addanumber")
	public String addANumber(Model model,
			@ModelAttribute("workerForm") WorkerForm workerForm) {
		model.addAttribute("jobTypes", jobTypes);
		model.addAttribute("locations", locations);
		workerForm.addAPhonenumber();
		return "worker/create";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST, params = "removenumbers")
	public String RemoveNumbers(Model model,
			@ModelAttribute("workerForm") WorkerForm workerForm) {
		model.addAttribute("jobTypes", jobTypes);
		model.addAttribute("locations", locations);
		workerForm.removePhonenumbers();
		return "worker/create";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model,
			@Valid @ModelAttribute("workerForm") WorkerForm workerForm,
			BindingResult result) {
		model.addAttribute("jobTypes", jobTypes);
		model.addAttribute("locations", locations);
		if (result.hasErrors()) {
			return "worker/create";
		}
		workerForm.setPassword(shaPasswordEncoder.encodePassword(workerForm.getPassword(), workerForm.getUsername()));
		workerService.create(workerForm);
		model.addAttribute("worker",
				workerService.read(workerForm.getUsername()));
		return "worker/created";
	}

	// worker profile page

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String showProfile(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Worker worker = workerService.read(auth.getName());
		model.addAttribute("worker", worker);
		return "worker/profile";
	}

	// update a worker

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String showUpdate(
			Model model,
			@ModelAttribute("updateWorkerForm") UpdateWorkerForm updateWorkerForm) {
		jobTypes = jobTypeService.findAll();
		locations = locationService.findAll();
		model.addAttribute("locations", locations);
		model.addAttribute("jobTypes", jobTypes);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Worker worker = workerService.read(auth.getName());
		updateWorkerForm.addExistingWorker(worker);
		return "worker/update";
	}

	@RequestMapping(value = "/doupdate", method = RequestMethod.POST, params = "addanumber")
	public String addANumberUpdating(
			Model model,
			@ModelAttribute("updateWorkerForm") UpdateWorkerForm updateWorkerForm) {
		model.addAttribute("jobTypes", jobTypes);
		model.addAttribute("locations", locations);
		updateWorkerForm.addAPhonenumber();
		return "worker/update";
	}

	@RequestMapping(value = "/doupdate", method = RequestMethod.POST, params = "removenumbers")
	public String RemoveNumbersUpdating(
			Model model,
			@ModelAttribute("updateWorkerForm") UpdateWorkerForm updateWorkerForm) {
		model.addAttribute("jobTypes", jobTypes);
		model.addAttribute("locations", locations);
		updateWorkerForm.removePhonenumbers();
		return "worker/update";
	}
	
	@RequestMapping(value = "/doupdate", method = RequestMethod.POST)
	public String doUpdate(
			Model model,
			@Valid @ModelAttribute("updateWorkerForm") UpdateWorkerForm updateWorkerForm,
			BindingResult result) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Worker worker = workerService.read(auth.getName());
		model.addAttribute("jobTypes", jobTypes);
		model.addAttribute("locations", locations);
		if (result.hasErrors()) {
			model.addAttribute("jobTypes", jobTypes);
			model.addAttribute("locations", locations);
			return "worker/update";
		}

		workerService.update(updateWorkerForm, worker);
		return "redirect:profile";
	}

	// workerside review handling

	@RequestMapping(value = "/reviews", method = RequestMethod.GET)
	public String showReviews(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Worker worker = workerService.read(auth.getName());
		List<Reviews> reviews = reviewService.readByWorkerId(worker.getId());
		model.addAttribute("worker", worker);
		model.addAttribute("reviews", reviews);
		return "worker/reviews";
	}

	@RequestMapping(value = "/reviews", method = RequestMethod.POST)
	public String reportReviews(Model model,
			@RequestParam(required = false) Long[] reports) {
		if (reports == null) {
			return "redirect:";
		}

		List<Long> reportList = Arrays.asList(reports);
		for (Long idReview : reportList) {
			Reviews review = reviewService.read(idReview);
			if (review.isReported() != true) {
				review.setReported(true);
				reviewService.update(review);
				Customer customer = review.getCustomer();
				long addOne = customer.getNumberOfReportedReviews() + 1;
				customer.setNumberOfReportedReviews(addOne);
				customerService.update(customer);
			}
		}
		return "redirect:reviews";
	}

}
