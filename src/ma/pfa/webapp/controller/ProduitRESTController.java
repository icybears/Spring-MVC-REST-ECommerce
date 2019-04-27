package ma.pfa.webapp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.pfa.webapp.dao.ICategorieDao;
import ma.pfa.webapp.dao.ICooperativeDao;
import ma.pfa.webapp.dao.IProduitDao;
import ma.pfa.webapp.model.Categorie;
import ma.pfa.webapp.model.Cooperative;
import ma.pfa.webapp.model.Produit;

@RestController
@Transactional
@RequestMapping("api/v1")
public class ProduitRESTController {
	
	@Autowired
	private IProduitDao prodDao;

	@Autowired
	private ICategorieDao catDao;
	
	@Autowired
	private ICooperativeDao coopDao;
	
	@GetMapping("/produits")
	public List<Produit> index() {
		return prodDao.findAll();
	}
	
	@GetMapping("/produits/{id}")
	public ResponseEntity<Produit> read(@PathVariable("id") int id) {
		Produit prod = prodDao.findById(id);
		if(prod == null) {
			 return new ResponseEntity<Produit>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Produit>(prod, HttpStatus.OK);
		}
	}
	
	@PostMapping("/produits")
	public int create(@RequestBody Produit prod) {
		return prodDao.save(prod);
	}
	
	@PutMapping("/produits/{id}")
	public Produit update(@PathVariable("id") int id,@RequestBody Produit prod) {
		return prodDao.update(prod);
	}
	
	@DeleteMapping("/produits/{id}")
	public void delete(@PathVariable("id") int id) {
		Produit prod= prodDao.findById(id);
		prodDao.delete(prod);
	}
	
	/* Produits par matiere premiere*/
	@GetMapping("/produits/matiere-premiere/{id}")
	public ResponseEntity<Set<Produit>> getByMatierePremiere(@PathVariable("id") int id) {
		Set<Produit> prods = prodDao.getProduitsByMatierePremiere(id);
		if(prods == null) {
			 return new ResponseEntity<Set<Produit>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Set<Produit>>(prods, HttpStatus.OK);
		}
	}
	
	/* Modifier categorie et cooperative du produit */
	
	@PutMapping("/produits/{idProd}/categorie/{catId}")
	public ResponseEntity<Produit> updateCategorie(@PathVariable("idProd") int idProd, @PathVariable("catId") int catId) {
		
		Categorie cat = catDao.findById(catId);
		Produit prod = prodDao.findById(idProd);
		
		if(cat == null || prod == null) {
			return new ResponseEntity<Produit>(HttpStatus.BAD_REQUEST);
		} else {
			prod.setCategorie(cat);
			prod = prodDao.update(prod);
			return new ResponseEntity<Produit>(prod, HttpStatus.OK);
		}
		
	}
	
	@PutMapping("/produits/{idProd}/cooperative/{coopId}")
	public ResponseEntity<Produit> updateCooperative(@PathVariable("idProd") int idProd, @PathVariable("coopId") int coopId) {
		
		Cooperative coop = coopDao.findById(coopId);
		Produit prod = prodDao.findById(idProd);
		
		if(coop == null || prod == null) {
			return new ResponseEntity<Produit>(HttpStatus.BAD_REQUEST);
		} else {
			prod.setCooperative(coop);
			prod = prodDao.update(prod);
			return new ResponseEntity<Produit>(prod, HttpStatus.OK);
		}
		
	}
	
}
