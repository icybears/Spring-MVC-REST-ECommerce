package ma.pfa.webapp.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ma.pfa.webapp.dao.ICooperativeDao;
import ma.pfa.webapp.model.Cooperative;

@RestController
@Transactional
@RequestMapping("api/v1")
public class CooperativeRESTController {

	@Autowired
	private ICooperativeDao coopDao;
	
	
	@GetMapping("/cooperatives")
	public List<Cooperative> index() {
		return coopDao.findAll();
	}
	
	@GetMapping("/cooperatives/{id}")
	public Cooperative read(@PathVariable("id") int id) {
		return coopDao.findById(id);
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cooperative> show(@PathVariable("id") int id) {
		Cooperative cooperative = coopDao.findById(id);
		if (cooperative == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Cooperative>(cooperative, HttpStatus.OK);
	}
}
