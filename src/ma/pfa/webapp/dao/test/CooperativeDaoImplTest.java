package ma.pfa.webapp.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.ICooperativeDao;
import ma.pfa.webapp.dao.IProduitDao;
import ma.pfa.webapp.model.Cooperative;
import ma.pfa.webapp.model.Produit;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class CooperativeDaoImplTest {

	@Autowired
	private ICooperativeDao coopDao;

	@Autowired
	private IProduitDao prodDao;

	static Cooperative coop;

	@Test
	void testCrud() {
		coop = new Cooperative();
		coop.setDescription("Cooperative 1");
		int id = coopDao.save(coop);
		coop = new Cooperative();
		coop.setDescription("Cooperative 2");
		assertNotNull(coopDao.save(coop));

		assertEquals(2, coopDao.findAll().size());

		Cooperative coopEntity = coopDao.findById(id);
		coopEntity.setDescription("Updated Cooperative");
		coopEntity = coopDao.update(coopEntity);

		assertEquals("Updated Cooperative", coopEntity.getDescription());

		coopDao.delete(coopEntity);

		assertNull(coopDao.findById(coopEntity.getId()));
	}

	@Test
	void getProduits() {
		Cooperative coop = new Cooperative("Coop1");
		int coopId = coopDao.save(coop);
		Cooperative coopEntity = coopDao.findById(coopId);

		Produit prod = new Produit("Prod 1", 150);
		prod.setCooperative(coopEntity);
		prodDao.save(prod);

		prod = new Produit("Prod 2", 350);
		prod.setCooperative(coopEntity);
		prodDao.save(prod);

		prod = new Produit("Prod 3", 90);
		prod.setCooperative(coopEntity);
		prodDao.save(prod);
		
		prod = new Produit("Prod 4", 120);
		prodDao.save(prod);
		
		assertEquals(3,coopDao.getProduits(coopEntity.getId()).size());

	}

}
