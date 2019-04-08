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

import ma.pfa.webapp.dao.ICategorieDao;
import ma.pfa.webapp.dao.IProduitDao;
import ma.pfa.webapp.model.Categorie;
import ma.pfa.webapp.model.Cooperative;
import ma.pfa.webapp.model.Produit;


@RestController
@Transactional
@RequestMapping("api/v1")
public class CategorieRESTController {

	@Autowired
	private ICategorieDao catDao;
	
	
	
	@GetMapping("/categories")
	public List<Categorie> index() {
		return catDao.findAll();
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<Categorie> read(@PathVariable("id") int id) {
		Categorie cat = catDao.findById(id);
		if(cat == null) {
			 return new ResponseEntity<Categorie>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Categorie>(cat,HttpStatus.OK);
		}
	}
	
	@PostMapping("/categories")
	public int create(@RequestBody Categorie cat) {
		return catDao.save(cat);
	}
	
	@PutMapping("/categories/{id}")
	public Categorie update(@PathVariable("id") int id,@RequestBody Categorie cat) {
		return catDao.update(cat);
	}
	
	@DeleteMapping("/categories/{id}")
	public void delete(@PathVariable("id") int id) {
		Categorie cat = catDao.findById(id);
		catDao.delete(cat);
	}
	
	/* les produits de la categorie */
	
	@GetMapping("/categories/{id}/produits")
	public Set<Produit> showProduit(@PathVariable("id") int catId ) {
		return catDao.getProduits(catId);
	}
	
}