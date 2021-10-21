package net.codejava;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		
		repo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/list_users")
	public String viewUsersList(Model model) {
		List<User> lisUsers = repo.findAll();
		model.addAttribute("listUsers", lisUsers);
		
		return "users";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)  
	  public String delete(@RequestParam("id") Long Id, Model model) {  
	    repo.deleteById(Id);
	    return "redirect:/";  
	  }
	@RequestMapping(value = "/edit", method = RequestMethod.GET)  
	  public String editUser(@RequestParam("id") Long userId, Model model) {  
	    Optional<User>  userEdit = repo.findById(userId);
	    userEdit.ifPresent(user -> model.addAttribute("user", user));  
	    return "editUser";  
	  } 

}
