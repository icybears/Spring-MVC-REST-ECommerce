package ma.pfa.webapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ma.pfa.webapp.model.Panier;
import ma.pfa.webapp.model.Produit;

class PanierTest {

	@Test
	void test() {
		Panier panier = new Panier();

		Produit prod = new Produit("Produit 1", 120);
		prod.setId(10993);
		Produit prod2 = new Produit("Produit 2", 150);
		prod2.setId(15393);

		panier.addItem(prod, 2);
		
		
		assertEquals(1,	panier.getItems().size());
		assertEquals(2, panier.findLigneCommandeByProductId(10993).getQuantite());
		
		panier.addItem(prod2, 5);
		
		assertEquals(2,	panier.getItems().size());
		assertEquals(5, panier.findLigneCommandeByProductId(15393).getQuantite());
		
		
	}

}
