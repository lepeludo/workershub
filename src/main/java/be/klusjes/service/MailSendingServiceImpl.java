package be.klusjes.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import be.klusjes.entities.User;

@Service
public class MailSendingServiceImpl {

	@Autowired
	private MailSender mailSender;

	public MailSendingServiceImpl() {
	}

	public void sendMail(String from, String to, String subject, String msg) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(msg);
		mailSender.send(simpleMailMessage);

	}
	
	public void sendConfirmationEmail(User user){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();		
		String url = "http://localhost:8080"+request.getContextPath()+"/confirmation?confirm=";
		String url2 = "&username="+user.getUsername();
		String msg = "Welcome to WorkersPoint!\r\nTo confirm your registration please visit\r\n"+url+user.getRandomUUID()+url2;
		if(user.getAuthority().getAuthority().equals("worker")){
		sendMail(null, user.getWorker().getEmail(), "Welcome to WorkersPoint", msg);
		}else if(user.getAuthority().getAuthority().equals("customer")){
			sendMail(null, user.getCustomer().getEmail(), "Welcome to WorkersPoint", msg);
		}
	}
	

}
