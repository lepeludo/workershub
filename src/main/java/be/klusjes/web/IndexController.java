package be.klusjes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping(value="/info",method=RequestMethod.GET)
	public String showIndexInfo(){
		return "i";
	}
	
	
}
