package spzc.mtd.project_decoy_mtd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UserController {

	List<User> sessionUsers = new ArrayList<User>();

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

		if(!arePasswordsCorrect(user)) {
			return "wrongCredentials";
		}

		return "loggedIn";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, params = "createAccountTrial")
	public String createAccountCheck(@ModelAttribute User user, Model model) {

		model.addAttribute("user", user);
		System.out.println("Post Create");
		System.out.println("Current user num is: " + sessionUsers.size());
		System.out.println("Id: " + user.getName());
		System.out.println("pwdHash: " + user.getPwdHash());

		if(!isUserCreated(user)) {
			System.out.println("[OK] Unique ID. User: " + user.getName() + " not found.");
			sessionUsers.add(user);
			return "result";
		} else {
			System.out.println("User: " + user.getName() + " already exists!");
			return "userExists";
		}

	}

	public Boolean isUserCreated(User user) {
		for(User currentUser : sessionUsers) {
			String currentUserName = currentUser.getName();
			String userName = user.getName();
			if(currentUserName.equals(userName)) {
				return true;
			}
		}
		return false;
	}

	public Boolean arePasswordsCorrect(User user) {

		for(User checkedUser : sessionUsers) {
			String currentUserName = checkedUser.getName();
			String userName = user.getName();

			if(currentUserName.equals(userName)) {
				String checkedUserPwdHash = checkedUser.getPwdHash();
				String userPwdHash = user.getPwdHash();
				System.out.println("Porownuje hasla: " + checkedUserPwdHash + " " + userPwdHash);
				return checkedUserPwdHash.equals(userPwdHash);
			}
		}

		return false;
	}

}
