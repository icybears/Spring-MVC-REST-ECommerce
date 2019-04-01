package ma.pfa.webapp.dao.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.IMatierePremiereDao;
import ma.pfa.webapp.dao.IOrigineDao;
import ma.pfa.webapp.model.Client;
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

	static Origine origine;

	static MatierePremiere mp;

	static int idMp;

	@BeforeAll
	static void setUp() {
		origine = new Origine();

		origine.setNom("Test Origine");
	}



	@Test
	public void testFindById() {
		

		int origineId = origineDao.save(origine);

		origine = origineDao.findById(origineId);

		// creation de la matiere premiere avec son origine
		mp = new MatierePremiere("Mp1", "Desc1", origine);
	
		idMp = mpDao.save(mp);
		// test findById()
		assertEquals("Test Origine", mpDao.findById(idMp).getOrigine().getNom());

	}

	@Test
	public void testUpdate() {
		

		int origineId = origineDao.save(origine);

		origine = origineDao.findById(origineId);

		// creation de la matiere premiere avec son origine
		mp = new MatierePremiere("Mp1", "Desc1", origine);
		
		idMp = mpDao.save(mp);

		mp = mpDao.findById(idMp);

		origine = new Origine();

		origine.setNom("New Origine");

		origineId = origineDao.save(origine);

		origine = origineDao.findById(origineId);

		// on change l'origine de la matiere premiere
		mp.setOrigine(origine);

		assertEquals("New Origine", mpDao.update(mp).getOrigine().getNom());

	}

	@Test
	public void testDelete() {

		int origineId = origineDao.save(origine);

		origine = origineDao.findById(origineId);

		mp = new MatierePremiere("Mp1", "Desc1", origine);

		idMp = mpDao.save(mp);

		mp = mpDao.findById(idMp);

		mpDao.delete(mp);

		assertNull(mpDao.findById(idMp));
	}

	@Test
	public void testFindAll() {

		int origineId = origineDao.save(origine);

		origine = origineDao.findById(origineId);


		mp = new MatierePremiere("Mp A", "desc A", origine);
		mpDao.save(mp);
		mp = new MatierePremiere("Mp B", "desc B", origine);
		mpDao.save(mp);

		assertEquals(2, mpDao.findAll().size());
	}
	
	@AfterAll
	static void tearDown() {
		origine = null;
	}


}
