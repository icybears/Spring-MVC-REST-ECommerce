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

import ma.pfa.webapp.dao.IMatierePremiereDao;
import ma.pfa.webapp.model.MatierePremiere;



@RestController
@Transactional
@RequestMapping("api/v1")
public class MatierePremiereRESTController {

	@Autowired
	private IMatierePremiereDao mpDao;
	
	
	
	@GetMapping("/matieres-premieres")
	public List<MatierePremiere> index() {
		return mpDao.findAll();
	}
	
	@GetMapping("/matieres-premieres/{id}")
	public ResponseEntity<MatierePremiere> read(@PathVariable("id") int id) {
		MatierePremiere mp = mpDao.findById(id);
		if(mp == null) {
			 return new ResponseEntity<MatierePremiere>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<MatierePremiere>(mp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/matieres-premieres")
	public int create(@RequestBody MatierePremiere mp) {
		return mpDao.save(mp);
	}
	
	@PutMapping("/matieres-premieres/{id}")
	public MatierePremiere update(@PathVariable("id") int id,@RequestBody MatierePremiere mp) {
		return mpDao.update(mp);
	}
	
	@DeleteMapping("/matieres-premieres/{id}")
	public void delete(@PathVariable("id") int id) {
		MatierePremiere mp= mpDao.findById(id);
		mpDao.delete(mp);
	}

}
