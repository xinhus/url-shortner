package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

	@GetMapping("")
	public String index() {
		return "index";
	}
	
	@GetMapping("/analytics/{shortUrl:[a-zA-Z0-9]{6}}")
	public String analytics(@PathVariable("shortUrl") final String shortUrl, Model model) {
		model.addAttribute("shorUrl", shortUrl);
		return "analytics";
	}
}
