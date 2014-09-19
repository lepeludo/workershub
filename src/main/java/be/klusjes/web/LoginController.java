package be.klusjes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import be.klusjes.entities.User;
import be.klusjes.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping("/confirmation")
	public String confirmUser(@RequestParam String confirm,
			@RequestParam String username) {
		User user = userService.read(username);
		System.out.println(user.getUsername());
		if (user.getRandomUUID().equals(confirm)) {
			user.setEnabled(true);
			user.setRandomUUID(null);
			userService.update(user);
			return "confirmation/success";
		}
		return "confirmation/failure";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin() {
		return "login/login";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotPasswordGet(
			@ModelAttribute("usernameForm") usernameForm usernameForm) {
		return "login/forgotpassword";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String forgotPasswordPost(
			@ModelAttribute("usernameForm") usernameForm usernameForm,
			BindingResult result) {
		System.out.println("hallo");
		System.out.println(usernameForm.getUsername());
		User user = userService.read(usernameForm.getUsername());
		if (user == null) {
			ObjectError arg0 = new ObjectError("username",
					"username does not exist");
			result.addError(arg0);
			return "login/forgotpassword";
		}
		if (!result.hasErrors()) {
			System.out.println("updating");
			userService.updatePassword(user);
		}

		return "login/passwordsend";
	}

}
