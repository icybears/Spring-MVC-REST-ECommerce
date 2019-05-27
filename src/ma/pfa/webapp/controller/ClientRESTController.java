package ma.pfa.webapp.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ma.pfa.webapp.dao.IClientDao;

import ma.pfa.webapp.model.Client;

import ma.pfa.webapp.model.User;


@RestController
@Transactional
@RequestMapping("api/v1")
public class ClientRESTController {

	@Autowired
	private IClientDao clDao;
	
	
	
	@GetMapping("/profile")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Client> profile(Authentication authentication) {
		
        Client cl = clDao.getClientByUsername(authentication.getName());
        
        if(cl == null) {
			 return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Client>(cl,HttpStatus.OK);
		}
    
	}
	
	@GetMapping("/clients")
	public List<Client> index() {
		return clDao.findAll();
	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> read(@PathVariable("id") int id) {
		Client cl = clDao.findById(id);
		if(cl == null) {
			 return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Client>(cl,HttpStatus.OK);
		}
	}
	
	@PostMapping("/clients")
	public int create(@RequestBody Client cl) {
		return clDao.save(cl);
	}
	
	@PutMapping("/clients/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Client> update(@PathVariable("id") int id,@RequestBody Client cl,Authentication authentication) {
		
		Client authenticatedClient = clDao.getClientByUsername(authentication.getName());
		User userHandle = authenticatedClient.getUser();
		Client requestClient = clDao.findById(cl.getId());
		
		Client client = null;
		//verifier si l'utilisateur connecté est le client qu'il essaye de modifier
		if(authenticatedClient.getId() == requestClient.getId()) {
				cl.setUser(userHandle);
			 client = clDao.update(cl);
			return new ResponseEntity<Client>(client,HttpStatus.OK);
		} else {
			return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/clients/{id}")
	public void delete(@PathVariable("id") int id) {
		Client cl = clDao.findById(id);
		clDao.delete(cl);
	}
	
	
	
}