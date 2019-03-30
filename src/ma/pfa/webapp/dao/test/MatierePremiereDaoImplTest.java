package ma.pfa.webapp.dao.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.IMatierePremiereDao;
import ma.pfa.webapp.dao.IOrigineDao;
import ma.pfa.webapp.model.MatierePremiere;
import ma.pfa.webapp.model.Origine;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional

class MatierePremiereDaoImplTest {
	
	@Autowired
	private IMatierePremiereDao mpDao; 

	@Autowired
	private IOrigineDao origineDao;


	
	
	
	@Test
	public void testMatierePremiere() {
		//creation de 2 origine 
		Origine origine1 = new Origine();
		Origine origine2 = new Origine();
		origine1.setNom("Test Origine");
		origine2.setNom("New Origine");
		
		int origine1Id = origineDao.save(origine1);
		int origine2Id = origineDao.save(origine2);
		
		origine1 = origineDao.findById(origine1Id);
		origine2 = origineDao.findById(origine2Id);
		
		//creation de la matiere premiere avec son origine
		MatierePremiere mp = new MatierePremiere("Mp1","Desc1", origine1);
		
		int idMp = mpDao.save(mp);
		//test findAll()
		assertNotNull(mpDao.findAll());
		//test findById() 
		assertEquals("Test Origine",mpDao.findById(idMp).getOrigine().getNom());
		
		mp = mpDao.findById(idMp);
		//on change l'origine de la matiere premiere
		mp.setOrigine(origine2);
		
		//test update()	
		assertEquals("New Origine",mpDao.update(mp).getOrigine().getNom());
		
		//test delete()
		mpDao.delete(mp);
		
		assertNull(mpDao.findById(idMp));
		
	}
	

	


}
