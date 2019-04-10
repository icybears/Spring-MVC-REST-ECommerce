package ma.pfa.webapp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.pfa.webapp.dao.ICommandeClientDao;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.Etat;
import ma.pfa.webapp.model.LigneCommande;
import ma.pfa.webapp.model.Produit;

@RestController
@Transactional
@RequestMapping("api/v1")
public class CommandeClientRESTController {

	@Autowired
	private ICommandeClientDao cmdDao;
	
	
	
	@GetMapping("/commandes-clients")
	public List<CommandeClient> index() {
		return cmdDao.findAll();
	}
	
	@GetMapping("/commandes-clients/{id}")
	public ResponseEntity<CommandeClient> read(@PathVariable("id") int id) {
		CommandeClient cmd = cmdDao.findById(id);
		if(cmd == null) {
			 return new ResponseEntity<CommandeClient>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<CommandeClient>(cmd,HttpStatus.OK);
		}
	}
	
	@PostMapping("/commandes-clients")
	public int create(@RequestBody CommandeClient cmd) {
		return cmdDao.save(cmd);
	}
	
	@PutMapping("/commandes-clients/{id}")
	public CommandeClient update(@PathVariable("id") int id,@RequestBody CommandeClient cmd) {
		return cmdDao.update(cmd);
	}
	
	@DeleteMapping("/commandes-clients/{id}")
	public void delete(@PathVariable("id") int id) {
		CommandeClient cmd = cmdDao.findById(id);
		cmdDao.delete(cmd);
	}
	
	/* Mettre à jours l'etat de la commande */
	@PutMapping("/commandes-clients/{id}/etat")
	public CommandeClient updateEtat(@PathVariable("id") int id,@RequestBody Etat etat) {
		
		CommandeClient cmd = cmdDao.findById(id);
		cmd.setEtat(etat);
		
		return cmdDao.update(cmd);
	}
	
	/*Afficher les lignes de commandes */
	
	@GetMapping("/commandes-clients/{id}/lignes-de-commandes")
	public Set<LigneCommande> getLignes(@PathVariable("id") int id){
		return cmdDao.getLigneCommandes(id);
	}
	
	
	
}