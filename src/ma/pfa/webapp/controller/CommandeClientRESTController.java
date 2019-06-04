package ma.pfa.webapp.controller;

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
import ma.pfa.webapp.message.request.PanierClient;
import ma.pfa.webapp.model.Client;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.Etat;
import ma.pfa.webapp.model.LigneCommande;
import ma.pfa.webapp.model.Panier;
import ma.pfa.webapp.model.User;
import ma.pfa.webapp.service.ICommandeClientService;

@RestController
@RequestMapping("api/v1")
public class CommandeClientRESTController {

	@Autowired
	private ICommandeClientService cmdService;
	
	@Autowired
	private IClientDao clDao;
	
	
	@GetMapping("/commandes-clients")
	public List<CommandeClient> index() {
		return cmdService.getAll();
	}
	
	@GetMapping("/commandes-clients/{id}")
	public ResponseEntity<CommandeClient> read(@PathVariable("id") int id) {
		CommandeClient cmd = cmdService.getById(id);
		if(cmd == null) {
			 return new ResponseEntity<CommandeClient>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<CommandeClient>(cmd,HttpStatus.OK);
		}
	}
	
	@PostMapping("/commandes-clients")
	public int create(@RequestBody CommandeClient cmd) {
		return cmdService.add(cmd);
	}
	
	@PutMapping("/commandes-clients/{id}")
	public CommandeClient update(@PathVariable("id") int id,@RequestBody CommandeClient cmd) {
		return cmdService.edit(cmd);
	}
	
	@DeleteMapping("/commandes-clients/{id}")
	public void delete(@PathVariable("id") int id) {
		CommandeClient cmd = cmdService.getById(id);
		cmdService.remove(cmd);
	}
	
	/* Mettre à jours l'etat de la commande */
	@PutMapping("/commandes-clients/{id}/etat")
	public CommandeClient updateEtat(@PathVariable("id") int id,@RequestBody Etat etat) {
		
		CommandeClient cmd = cmdService.getById(id);
		cmd.setEtat(etat);
		
		return cmdService.edit(cmd);
	}
	
	/*Afficher les lignes de commandes */
	
	@GetMapping("/commandes-clients/{id}/lignes-de-commandes")
	public Set<LigneCommande> getLignes(@PathVariable("id") int id){
		return cmdService.getAllLigneCommande(id);
	}
	
	/* Enregistrer commandes depuis un panier */
	@PostMapping("/save-commande")
	@Transactional
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<CommandeClient> saveCommande(@RequestBody PanierClient panierClient,Authentication authentication) {
		
		Set<LigneCommande> items = panierClient.getItems();
		Client client = panierClient.getClient();
		
		if(authentication != null) {
			System.out.println("Client is logged in");
			Client authenticatedClient = clDao.getClientByUsername(authentication.getName());
			//on associe le client authentifié avec le client de la requête
			client.setId(authenticatedClient.getId());
			client.setUser(authenticatedClient.getUser());
			//mettre à jours les informations du client authentifié
			client = clDao.update(client);
			
		} else {
			System.out.println("Client is a guest");
			//on crée le nouveau client
			if(client != null)
			client = clDao.findById(clDao.save(client));
		}
		System.out.println(items.size());
		
		Panier panier = new Panier();
		for (LigneCommande ligne : items) {
			panier.addItem(ligne);
		}
		
		
		CommandeClient cmd = cmdService.saveCommande(panier, client);
		
		
		
		if(cmd == null || client == null) {
			 return new ResponseEntity<CommandeClient>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<CommandeClient>(cmd,HttpStatus.OK);
		}
	}
	
}