package spzc.mtd.project_decoy_mtd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@GetMapping("/user")
	public String greetingForm(Model model) {
		model.addAttribute("user", new User());
		System.out.println("Get");
		return "user";
	}

	@PostMapping("/user")
	public String greetingSubmit(@ModelAttribute User greeting, Model model) {
		model.addAttribute("user", greeting);
		System.out.println("Post");
		return "result";
	}

}
