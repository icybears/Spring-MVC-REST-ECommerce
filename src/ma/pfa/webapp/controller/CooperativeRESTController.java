package ma.pfa.webapp.controller;

import java.util.ArrayList;
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

import ma.pfa.webapp.dao.ICooperativeDao;
import ma.pfa.webapp.dao.IProduitDao;
import ma.pfa.webapp.model.Cooperative;
import ma.pfa.webapp.model.Produit;

@RestController
@Transactional
@RequestMapping("api/v1")
public class CooperativeRESTController {

	@Autowired
	private ICooperativeDao coopDao;
	
	@Autowired private IProduitDao prodDao;
	
	
	@GetMapping("/cooperatives")
	public List<Cooperative> index() {
		return coopDao.findAll();
	}
	
	@GetMapping("/cooperatives/{id}")
	public ResponseEntity<Cooperative> read(@PathVariable("id") int id) {
		Cooperative coop = coopDao.findById(id);
		if(coop == null) {
			 return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Cooperative>(coop,HttpStatus.OK);
		}
	}
	
	@PostMapping("/cooperatives")
	public int create(@RequestBody Cooperative coop) {
		return coopDao.save(coop);
	}
	
	@PutMapping("/cooperatives/{id}")
	public Cooperative update(@PathVariable("id") int id,@RequestBody Cooperative coop) {
		return coopDao.update(coop);
	}
	
	@DeleteMapping("/cooperatives/{id}")
	public void delete(@PathVariable("id") int id) {
		Cooperative coop = coopDao.findById(id);
		coopDao.delete(coop);
	}

	/* CRUD sur les produits de la cooperative */
	
	@GetMapping("/cooperatives/{id}/produits")
	public Set<Produit> showProduit(@PathVariable("id") int coopId ) {
		return coopDao.getProduits(coopId);
	}
	
	@PostMapping("/cooperatives/{id}/produits")
	public void createProduit(@PathVariable("id") int coopId, @RequestBody Produit prod ) {
		Cooperative coopEntity = coopDao.findById(coopId);
		coopEntity.addProduit(prod);
		coopDao.update(coopEntity);
	}
	
	@PutMapping("/cooperatives/{id}/produits/{idProd}")
	public Produit updateProduit( @PathVariable("idProd") int idProd, @RequestBody Produit prod) {
		return prodDao.update(prod);
		
	}
	@DeleteMapping("/cooperatives/{id}/produits/{idProd}")
	public void deleteProduit(@PathVariable("id") int idCoop, @PathVariable("idProd") int idProd) {
		Cooperative coop = coopDao.findById(idCoop);
		Produit prod = prodDao.findById(idProd);
		
		coop.removeProduit(prod);
		coopDao.update(coop);
		
		prodDao.delete(prod);
		
	}
	
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Cooperative> show(@PathVariable("id") int id) {
//		Cooperative cooperative = coopDao.findById(id);
//		if (cooperative == null)
//			return new ResponseEntity(HttpStatus.NOT_FOUND);
//		else
//			return new ResponseEntity<Cooperative>(cooperative, HttpStatus.OK);
//	}
}
