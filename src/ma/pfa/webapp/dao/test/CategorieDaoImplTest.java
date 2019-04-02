package ma.pfa.webapp.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.ICategorieDao;
import ma.pfa.webapp.dao.IProduitDao;
import ma.pfa.webapp.model.Categorie;
import ma.pfa.webapp.model.Produit;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class CategorieDaoImplTest {

	@Autowired
	private ICategorieDao categorieDao;
	
	@Autowired
	private IProduitDao prodDao;
	
	@Autowired
	private SessionFactory sessFactory;
	
	@Test
	@Disabled
	void testSave() throws Exception{
		Categorie cat = new Categorie();
		cat.setNom("Categorie 1");
		
		assertNotNull(categorieDao.save(cat));	
	}

	@Test
	@Disabled
	void testFindById() {
		Categorie cat = new Categorie();
		cat.setNom("Categorie 1");
		int id = categorieDao.save(cat);
		
		Categorie catEntity = categorieDao.findById(id);
		assertEquals("Categorie 1", catEntity.getNom());
	}

	@Test
	@Disabled
	void testUpdate() {
		Categorie cat = new Categorie();
		cat.setNom("Categorie 1");
		int id = categorieDao.save(cat);
		
		
		Categorie catEntity = categorieDao.findById(id);
		
		catEntity.setNom("Updated Categorie");
		
		assertEquals("Updated Categorie", categorieDao.update(catEntity).getNom());
	}

	@Test
	@Disabled
	void testDelete() {
		Categorie cat = new Categorie();
		cat.setNom("Categorie 1");
		int id = categorieDao.save(cat);
		Categorie catEntity = categorieDao.findById(id);
		
		 categorieDao.delete(catEntity);
		 
		 assertNull(categorieDao.findById(id));
		
	}

	@Test
	@Disabled
	void testFindAll() {
		Categorie cat = new Categorie();
		cat.setNom("Categorie 1");
		categorieDao.save(cat);
		cat = new Categorie();
		cat.setNom("Categorie 2");
		categorieDao.save(cat);
		cat = new Categorie();
		cat.setNom("Categorie 3");
		categorieDao.save(cat);
		
		assertEquals(3, categorieDao.findAll().size());
	}

	@Test
	void testGetProduits() {
		
		//Creation d'une categorie
		Categorie cat = new Categorie();
		cat.setNom("Categorie 1");
		System.out.println("save categorie");
		int idCat = categorieDao.save(cat);
		System.out.println("get entity categorie");
		Categorie catEntity = categorieDao.findById(idCat);
		
		//Creation de 4 produits , 3 de meme categorie
		Produit prod = new Produit("P1",100);
		prod.setCategorie(catEntity);
		prodDao.save(prod);
		
		prod = new Produit("P2",150);
		prod.setCategorie(catEntity);
		prodDao.save(prod);
		
		prod = new Produit("P3",200);
		prod.setCategorie(catEntity);
		prodDao.save(prod);
		
		prod = new Produit("P4",130);
		prodDao.save(prod);
		
		
		//test get produit from categorie
		assertEquals(3,categorieDao.getProduits(idCat).size());
		
	}
}
