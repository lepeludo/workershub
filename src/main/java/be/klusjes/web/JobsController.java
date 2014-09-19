package be.klusjes.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import be.klusjes.entities.Customer;
import be.klusjes.entities.Reviews;
import be.klusjes.entities.Worker;
import be.klusjes.service.CustomerService;
import be.klusjes.service.JobTypeService;
import be.klusjes.service.LocationService;
import be.klusjes.service.ReviewService;
import be.klusjes.service.WorkerService;

@Controller
@RequestMapping("/jobs")
public class JobsController {
	/*
	 * This Controller allows customers to handle everything workerrelated
	 */

	@Autowired
	CustomerService customerService;
	@Autowired
	JobTypeService jobTypeService;
	@Autowired
	LocationService locationService;
	@Autowired
	WorkerService workerService;
	@Autowired
	ReviewService reviewService;

	// allows finding workers by keyword and by jobtype/location
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String showStartSearch(Model model,
			@ModelAttribute("workersForm") WorkersForm workersForm) {
		model.addAttribute("jobTypes", jobTypeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		return "jobs/search";
	}

	/*
	 * altered
	 */
	@RequestMapping(value = "/dosearch", method = RequestMethod.POST, params = "locationtype")
	public String doSearch(Model model,
			@Valid @ModelAttribute("workersForm") WorkersForm workersForm,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("jobTypes", jobTypeService.findAll());
			model.addAttribute("locations", locationService.findAll());
			return "jobs/search";
		}
		List<Worker> relevantWorkers = workerService
				.findWorkersByJobTypeAndLocation(workersForm.getJobTypesLong(),
						workersForm.getLocationLong());
		model.addAttribute("workers", relevantWorkers);
		return "jobs/search";
	}

	// search workers by keywords
	@RequestMapping(value = "/dosearch", method = RequestMethod.POST, params = "keyword")
	public String doSearchWorkersByName(Model model,
			@RequestParam String keyword,
			@ModelAttribute("workersForm") WorkersForm workersForm) {
		System.out.println("hey");
		List<Worker> workers = workerService.findCustomersByText(keyword);
		model.addAttribute("jobTypes", jobTypeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("workers", workers);
		return "jobs/search";

	}

	// show worker details
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String showDetails(Model model, @RequestParam Long id) {
		Worker worker = workerService.readById(id);
		model.addAttribute("worker", worker);
		return "jobs/workerdetail";
	}

	// let customer create a review
	@RequestMapping(value = "/writereview", method = RequestMethod.GET)
	public String showReviews(Model model,
			@ModelAttribute("reviewForm") ReviewForm reviewForm,
			@RequestParam Long id) {
		Worker worker = workerService.readById(id);
		model.addAttribute("worker", worker);
		return "jobs/writereview";
	}

	@RequestMapping(value = "/reviews", method = RequestMethod.POST)
	public String showReviews(Model model,
			@RequestParam(required = false) Integer rating,
			@ModelAttribute("reviewForm") ReviewForm reviewForm,
			BindingResult result) {

		Worker worker = workerService.readById(reviewForm.getWorkerId());
		if (result.hasErrors()) {
			return "jobs/reviews";
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Customer customer = customerService.read(auth.getName());
		Reviews review = reviewForm.convertReviewFormToReview(customer, worker);
		reviewService.create(review);
		if (rating != null) {
			worker.addReviewStars(rating);
			workerService.update(worker);
		}
		model.addAttribute("review", review);
		return "jobs/reviewwritten";
	}

	// handles reviews for worker users
	@RequestMapping(value = "/reviews", method = RequestMethod.GET)
	public String showReviewOfWorker(Model model, @RequestParam Long id) {
		Worker worker = workerService.readById(id);
		List<Reviews> reviews = reviewService.readByWorkerId(worker.getId());
		model.addAttribute("worker", worker);
		model.addAttribute("reviews", reviews);
		return "jobs/reviews";
	}

}
