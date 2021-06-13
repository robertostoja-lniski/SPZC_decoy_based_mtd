package spzc.mtd.project_decoy_mtd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

	@GetMapping("/user")
	public String showMainPage(Model model) {
		model.addAttribute("user", new User());
		System.out.println("Get");
		return "user";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, params = "loginTrial")
	public String loginCheck(@ModelAttribute User user, Model model) {

		model.addAttribute("user", user);
		System.out.println("Post Login");
		System.out.println("Id: " + user.getId());
		System.out.println("pwdHash: " + user.getPwdHash());
		return "result";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, params = "createAccountTrial")
	public String createAccountCheck(@ModelAttribute User user, Model model) {

		model.addAttribute("user", user);
		System.out.println("Post Create");
		System.out.println("Id: " + user.getId());
		System.out.println("pwdHash: " + user.getPwdHash());
		return "result";
	}

}
