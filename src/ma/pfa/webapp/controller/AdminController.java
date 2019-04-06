package ma.pfa.webapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
	@RequestMapping("/login")
	public String authenticate(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required=false) String logout, Model model ) {
		//check if login failed
		if(error != null) {
			model.addAttribute("error", "Nom d'utilisateur ou mot de passe invalide");
		}
		//check if user logged out
		if(logout != null) {
			model.addAttribute("msg","Deconnection avec succès!");
		}
		
		return "admin/adminLogin";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		model.addAttribute("userName", userName);
		return "admin/index";
	}
	
	@RequestMapping("/secret")
	public String secret() {
		System.out.println("inside secret url!");
		return "admin/secret";
		
	}
}
