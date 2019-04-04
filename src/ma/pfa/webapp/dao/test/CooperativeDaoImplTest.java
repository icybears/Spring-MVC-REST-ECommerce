package ma.pfa.webapp.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Disabled;
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
	@Disabled
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
	void testDeleteCooperativeCascade() {
		Cooperative coop = new Cooperative("Coop 1");

		coop.addProduit(new Produit("Prod 1", 150));
		coop.addProduit(new Produit("Prod 2", 130));

		int coopId = coopDao.save(coop);

		prodDao.save(new Produit("Prod X", 100));
		prodDao.save(new Produit("Prod Y", 120));

		Cooperative coopEntity = coopDao.findById(coopId);
		coopDao.delete(coopEntity);
	}

	@Test
	@Disabled
	void testRemoveProduit() {
		Cooperative coop = new Cooperative("Coop 1");
		int coopId = coopDao.save(coop);
		Cooperative coopEntity = coopDao.findById(coopId);
		Produit prod = new Produit("Prod 1", 150);
		prod.setCooperative(coopEntity);
		int prodId = prodDao.save(prod);
		Produit prodEntity = prodDao.findById(prodId);
		
		coopEntity = coopDao.findById(coopId);
		assertEquals(1,coopDao.getProduits(coopEntity.getId()).size());
		
		
		coopEntity.removeProduit(prodEntity);
		coopDao.update(coopEntity);
		
		/*
		 * -----------------------------------------------------------------------------
		 * -----
		 */
		/*
		 * PROBLEM: la suppression depuis cooperative ne supprime pas les produits de la
		 * bd!
		 */
		/*
		 * -----------------------------------------------------------------------------
		assertEquals(0,prodDao.findAll().size());

		
	}

	@Test
	@Disabled
	void testProduits() {
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
		int idProd = prodDao.save(prod);
		Produit prodEntity = prodDao.findById(idProd);

		prod = new Produit("Prod 4", 120);
		prodDao.save(prod);

		// test getProduits
		assertEquals(3, coopDao.getProduits(coopEntity.getId()).size());

		coopEntity = coopDao.findById(coopId);

		// adding a new Product to coop
		coopEntity.addProduit(new Produit("New Produit", 100));
		coopEntity = coopDao.update(coopEntity);

		assertEquals(4, coopDao.getProduits(coopEntity.getId()).size());

		// removing product "Prod 3" from coop
		coopEntity.removeProduit(prodEntity);
		coopEntity = coopDao.update(coopEntity);

		assertEquals(3, coopDao.getProduits(coopEntity.getId()).size());
		/*
		 * -----------------------------------------------------------------------------
		 * -----
		 */
		/*
		 * PROBLEM: la suppression depuis cooperative ne supprime pas les produits de la
		 * bd!
		 */
		/*
		 * -----------------------------------------------------------------------------
		 * -----
		 */
		assertEquals(3, prodDao.findAll().size());

	}

}
