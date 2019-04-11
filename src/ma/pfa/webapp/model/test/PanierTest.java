package ma.pfa.webapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.IProduitDao;
import ma.pfa.webapp.model.Panier;
import ma.pfa.webapp.model.Produit;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class PanierTest {
	
	@Autowired
	private IProduitDao prodDao;
	
	@Test
	void test() {
		Panier panier = new Panier();

		Produit prod = prodDao.findById(1);
		Produit prod2 = prodDao.findById(2);
		

		panier.addItem(prod, 2);
		
		
		assertEquals(1,	panier.getItems().size());
		assertEquals(2, panier.findLigneCommandeByProductId(prod.getId()).getQuantite());
		assertEquals("prodA",panier.getItems().get(prod.getId()).getProduit().getNom());
		
		panier.addItem(prod, 4);
		
		assertEquals(1,	panier.getItems().size());
		assertEquals(6, panier.findLigneCommandeByProductId(prod.getId()).getQuantite());
		
		panier.addItem(prod2, 5);
		
		assertEquals(2,	panier.getItems().size());
		assertEquals(5, panier.findLigneCommandeByProductId(prod2.getId()).getQuantite());
		
		panier.removeItem(1000);
		
		panier.removeItem(prod.getId());
		
		assertEquals(1,	panier.getItems().size());
		assertNull(panier.findLigneCommandeByProductId(prod.getId()));
		
	}

}
