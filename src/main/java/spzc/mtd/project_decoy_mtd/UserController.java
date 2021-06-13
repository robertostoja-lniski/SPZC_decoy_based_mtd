package spzc.mtd.project_decoy_mtd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class UserController {

	HashMap<String, User> sessionUsers = new HashMap<String, User>();

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
		System.out.println("Id: " + user.getName());
		System.out.println("pwdHash: " + user.getPwdHash());
		return "result";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, params = "createAccountTrial")
	public String createAccountCheck(@ModelAttribute User user, Model model) {

		model.addAttribute("user", user);
		System.out.println("Post Create");
		System.out.println("Id: " + user.getName());
		System.out.println("pwdHash: " + user.getPwdHash());

		if(!isUserCreated(user)) {
			System.out.println("User: " + user.getName() + " not found.");
			sessionUsers.put(user.getName(), user);
			return "result";
		} else {
			System.out.println("User: " + user.getName() + " already exists!");
			return "userExists";
		}

	}

	public Boolean isUserCreated(User user) {
		return sessionUsers.containsKey(user.getName());
	}

}
