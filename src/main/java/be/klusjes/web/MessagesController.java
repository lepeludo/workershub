package be.klusjes.web;

import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import be.klusjes.entities.Customer;
import be.klusjes.entities.Message;
import be.klusjes.entities.MessageThread;
import be.klusjes.entities.Worker;
import be.klusjes.service.CustomerService;
import be.klusjes.service.MessageService;
import be.klusjes.service.MessageThreadService;
import be.klusjes.service.WorkerService;

@Controller
@RequestMapping(value = "/messages")
@SessionAttributes
public class MessagesController {

	@Autowired
	WorkerService workerService;

	@Autowired
	CustomerService customerService;

	@Autowired
	MessageThreadService messageThreadService;

	@Autowired
	MessageService messageService;

	@Autowired
	CustomerController customerController;

	@Autowired
	WorkerController workerController;

	@RequestMapping(value = "/checkthreads", method = RequestMethod.GET)
	public String checkThreads(Model model, @RequestParam Long workerId) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Customer customer = customerService.read(auth.getName());
		List<MessageThread> messageThreads = messageThreadService
				.readByWorkerAndCustomerId(workerId, customer.getId());
		// if no threads exist
		if (messageThreads.isEmpty()) {
			model.addAttribute("workerId", workerId);
			return "redirect:startthread";
		}
		// if threads already exist
		else {
			model.addAttribute("messageThreads", messageThreads);
			model.addAttribute("customerId", customer.getId());
			model.addAttribute("workerId", workerId);
			return "messages/continuethreads";
		}
	}

	@RequestMapping(value = "/workerthreads", method = RequestMethod.GET)
	public String checkWorkerThreads(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Worker worker = workerService.read(auth.getName());
		List<MessageThread> messageThreads = messageThreadService
				.readByWorkerId(worker.getId());
		model.addAttribute("messageThreads", messageThreads);
		return "messages/continuethreads";
	}

	@RequestMapping(value = "/customerthreads", method = RequestMethod.GET)
	public String checkCustomerThreads(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Customer customer = customerService.read(auth.getName());
		List<MessageThread> messageThreads = messageThreadService
				.readByCustomerId(customer.getId());
		model.addAttribute("messageThreads", messageThreads);
		return "messages/continuethreads";
	}

	@RequestMapping(value = "/continuethreads", method = RequestMethod.POST)
	public String continueThread(Model model, HttpServletRequest request,
			@RequestParam Long messageThreadId,
			@ModelAttribute("messageForm") MessageForm messageForm) {
		MessageThread messageThread = messageThreadService
				.read(messageThreadId);
		model.addAttribute("messageThread", messageThread);
		List<Message> messages = messageService
				.findMessagesByMessageThreadIdSortedByTime(messageThread
						.getId());
		model.addAttribute("messages", messages);

		// set messages as read when viewed
		for (Message message : messages) {
			if (message.isWorkerBool() && request.isUserInRole("customer")) {
				message.setReadBool(true);
				messageService.update(message);
				

			} else if (!message.isWorkerBool()
					&& request.isUserInRole("worker")) {
				message.setReadBool(true);
				messageService.update(message);
				workerController.setMessageSessionParam(model);
			}
		}
		return "messages/activethread";
	}

	@RequestMapping(value = "/continuethreads", method = RequestMethod.POST, params = "answer")
	public String continueThreadAnswer(Model model, HttpServletRequest request,
			@ModelAttribute("messageForm") MessageForm messageForm,
			@RequestParam long messageThreadId) {
		MessageThread messageThread = messageThreadService
				.read(messageThreadId);
		if (request.isUserInRole("customer")) {
			messageForm.setWorkerBool(false);
			customerController.setMessageSessionParam(model);
		} else if (request.isUserInRole("worker")) {
			messageForm.setWorkerBool(true);
		}
		messageService.createMessageInExistingThreadFromForm(messageForm,
				messageThread);
		return "messages/messagesend";
	}

	@RequestMapping(value = "/startthread", method = RequestMethod.GET)
	public String startMessaging(
			Model model,
			@ModelAttribute("messageThreadForm") MessageThreadForm messageThreadForm,
			@RequestParam Long workerId) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Customer customer = customerService.read(auth.getName());
		model.addAttribute("customerId", customer.getId());
		model.addAttribute("workerId", workerId);
		return "messages/startthread";

	}

	@RequestMapping(value = "/startthread", method = RequestMethod.POST)
	public String receiveStartThread(
			Model model,
			@ModelAttribute("messageThreadForm") MessageThreadForm messageThreadForm,
			BindingResult result) {
		messageThreadForm.toString();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Customer customer = customerService.read(auth.getName());
		if (result.hasErrors()) {

			model.addAttribute("customerId", customer.getId());
			model.addAttribute("workerId", messageThreadForm.getWorkerId());
			return "startthread";
		} else {
			Worker worker = workerService.readById((long) 1);
			messageService.createThreadFromForm(messageThreadForm, customer,
					worker);
			return "messages/messagesend";
		}

	}

}
