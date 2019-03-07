package ma.pfa.webapp.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ma.pfa.webapp.model.Produit;

class ProduitDaoImplTest {
	
	@Autowired
	private IProduitDao produitDao;

	@Test
	void testingProduitDao() {
		Produit prod = new Produit("Prod1",100.5);
		produitDao.save(prod);
	}

}
