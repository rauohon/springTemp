package com.a.b;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.a.b.cmmn.utils.HwyMap;
import com.a.b.service.HomeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "homeService")
	private HomeService homeService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		log.info("Welcome home! The client locale is {}.", locale);
		
		HwyMap hwyMap = new HwyMap();
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		String dbTest = homeService.getDbTest(hwyMap);
		
		homeService.insertDbTest(hwyMap);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("dbTest", dbTest);
		
		log.info("{}", dbTest);
		
		return "home";
	}
	
}
