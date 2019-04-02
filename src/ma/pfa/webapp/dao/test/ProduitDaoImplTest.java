package ma.pfa.webapp.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.ICategorieDao;
import ma.pfa.webapp.dao.ICooperativeDao;
import ma.pfa.webapp.dao.IMatierePremiereDao;
import ma.pfa.webapp.dao.IProduitDao;
import ma.pfa.webapp.model.Categorie;
import ma.pfa.webapp.model.Cooperative;
import ma.pfa.webapp.model.MatierePremiere;
import ma.pfa.webapp.model.Produit;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class ProduitDaoImplTest {

	@Autowired
	private IProduitDao produitDao; 
	
	@Autowired
	private ICategorieDao catDao;
	
	@Autowired
	private ICooperativeDao coopDao;
	
	@Autowired
	private IMatierePremiereDao mpDao;
	
	@Test
	void testCrud() {
		
		Produit p1 = new Produit("p1", 130);
		Produit p2 = new Produit("p2",70);
		
		//save
		int id1 = produitDao.save(p1);
		int id2 = produitDao.save(p2);
		assertNotNull(id1);
		assertNotNull(id2);
		//findById
		assertEquals("p1",produitDao.findById(id1).getNom());
		assertEquals("p2",produitDao.findById(id2).getNom());
		//findAll
		assertEquals(2,produitDao.findAll().size());
		
		//update
		Produit prodEntity = produitDao.findById(id1);
		prodEntity.setNom("p1 updated");
		produitDao.update(prodEntity);
		assertEquals("p1 updated", produitDao.findById(id1).getNom());
		
		
	}
	
	@Test
	void testProduit() {
		
		Produit p1 = new Produit("p1",150);
		// new categorie
		Categorie cat = new Categorie();
		cat.setNom("cat1");
		int id = catDao.save(cat);
		// added categorie to produit.
		p1.setCategorie(catDao.findById(id));
		//new cooperative
		Cooperative coop = new Cooperative("Coop1");
		id = coopDao.save(coop);
		//added cooperative to produit
		p1.setCooperative(coopDao.findById(id));
		//saved produit to db
		id = produitDao.save(p1);
		
		Produit prodEntity = produitDao.findById(id);
		
		assertEquals("Coop1",prodEntity.getCooperative().getNom());
		assertEquals("cat1",prodEntity.getCategorie().getNom());
		
		//new set of MatierePremiere
		Set<MatierePremiere> mps = new HashSet<MatierePremiere>();
		
		//populating mps.
		id = mpDao.save(new MatierePremiere("mp 1"));
		mps.add(mpDao.findById(id));
		id = mpDao.save(new MatierePremiere("mp 2"));
		mps.add(mpDao.findById(id));
		
		//added set of mps to produit
		p1.setMatieresPremieres(mps);
		
		prodEntity = produitDao.update(p1);
		
		assertEquals(2,prodEntity.getMatieresPremieres().size());
		
	}

}
