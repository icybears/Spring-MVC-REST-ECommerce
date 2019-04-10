package ma.pfa.webapp.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.ICommandeClientDao;
import ma.pfa.webapp.dao.ILigneCommandeDao;
import ma.pfa.webapp.dao.IProduitDao;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.IdLigneCommande;
import ma.pfa.webapp.model.LigneCommande;
import ma.pfa.webapp.model.Produit;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class LigneCommandeDaoImplTest {

	@Autowired
	private ILigneCommandeDao lcDao;
	
	@Autowired
	private IProduitDao prodDao;
	
	@Autowired
	private ICommandeClientDao cmdDao;
	
	@Test
	void testCrud() {
		Produit prod = new Produit("prod AA",140);
		int idProd = prodDao.save(prod);
		Produit prodEntity = prodDao.findById(idProd);
		
		CommandeClient cmd = new CommandeClient();
		int idCmd = cmdDao.save(cmd);
		CommandeClient cmdEntity = cmdDao.findById(idCmd);
		
		
		LigneCommande lc = new LigneCommande(prodEntity,cmdEntity,5);
		IdLigneCommande idLc = lcDao.saveLigne(lc);
		
		assertNotNull(idLc);
		
//		LigneCommande lcEntity = lcDao.findByIdLigne(idLc);
//		
//		assertEquals(5, lcEntity.getQuantite());
//		assertEquals("prod AA", lcEntity.getProduit().getNom());
		
		LigneCommande lcEntity = lcDao.findByIdLigne(new IdLigneCommande(prodEntity,cmdEntity));
		assertEquals(5,lcEntity.getQuantite());
		assertEquals("prod AA", lcEntity.getProduit().getNom());
		
		lcEntity.setQuantite(9);
		lcDao.save(lcEntity);
		
		assertEquals(9, lcDao.findByIdLigne(idLc).getQuantite());
		
		
	}
	
	@Test
	@Disabled
	void test() {
		Produit prod = new Produit("prod 1FVL23M",100);
		int idProd = prodDao.save(prod);
		Produit prodEntity = prodDao.findById(idProd);
		
		LigneCommande lc = new LigneCommande();	
		lc.setProduit(prodEntity);
		
		lcDao.saveLigne(lc);
	}

}
