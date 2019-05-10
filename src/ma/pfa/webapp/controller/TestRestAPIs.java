package ma.pfa.webapp.controller;

import java.security.Principal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.pfa.webapp.dao.IClientDao;
import ma.pfa.webapp.model.Client;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestAPIs {
	
	@Autowired
	private IClientDao clDao;
	
	
	@GetMapping("/api/test/name")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@Transactional
	public String getName(Authentication authentication, Principal principal) {
        System.out.println(authentication.getName());
        System.out.println("-----------------");
        System.out.println(principal.getName());
        Client cl = clDao.getClientByUsername(authentication.getName());
        System.out.println("clDao result: "+cl.getAdresse());
        return "";
    }
	
	@GetMapping("/api/test/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return ">>> User Contents!";
	}

	@GetMapping("/api/test/pm")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public String projectManagementAccess() {
		return ">>> Project Management Board";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}
	
	
}