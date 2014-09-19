package be.klusjes.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.SessionAttributes;

import be.klusjes.entities.Message;
import be.klusjes.entities.MessageThread;

@ControllerAdvice
@SessionAttributes("unreadMessages")
public class MessageUtil {

	int unreadMessages = 0;

	public void setMessageSessionParam(Model model,
			List<MessageThread> messageThreads) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		// make list of authorities in string format
		List<String> authorityStrings = new ArrayList<>();

		for (GrantedAuthority temp : auth.getAuthorities()) {
			authorityStrings.add(temp.getAuthority());
		}
		// if authority == customer
		if (authorityStrings.contains("customer")) {
			for (MessageThread messageThread : messageThreads) {
				for (Message message : messageThread.getMessages()) {
					if (!message.isReadBool() && message.isWorkerBool()) {
						++unreadMessages;
					}
				}
			}
			model.addAttribute("unreadMessages", unreadMessages);
		}
		// if authority == worker
		if (authorityStrings.contains("worker")) {
			for (MessageThread messageThread : messageThreads) {
				for (Message message : messageThread.getMessages()) {
					if (!message.isReadBool() && !message.isWorkerBool()) {
						++unreadMessages;
					}
				}
			}
			model.addAttribute("unreadMessages", unreadMessages);
		}

	}

}
