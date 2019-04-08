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
import org.springframework.web.bind.annotation.RestController;

import ma.pfa.webapp.dao.IOrigineDao;
import ma.pfa.webapp.model.Origine;




@RestController
@Transactional
@RequestMapping("api/v1")
public class OrigineRESTController {

	@Autowired
	private IOrigineDao origineDao;
	
	
	
	@GetMapping("/origines")
	public List<Origine> index() {
		return origineDao.findAll();
	}
	
	@GetMapping("/origines/{id}")
	public ResponseEntity<Origine> read(@PathVariable("id") int id) {
		Origine org = origineDao.findById(id);
		if(org == null) {
			 return new ResponseEntity<Origine>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Origine>(org, HttpStatus.OK);
		}
	}
	
	@PostMapping("/origines")
	public int create(@RequestBody Origine org) {
		return origineDao.save(org);
	}
	
	@PutMapping("/origines/{id}")
	public Origine update(@PathVariable("id") int id,@RequestBody Origine org) {
		return origineDao.update(org);
	}
	
	@DeleteMapping("/origines/{id}")
	public void delete(@PathVariable("id") int id) {
		Origine org= origineDao.findById(id);
		origineDao.delete(org);
	}
}
