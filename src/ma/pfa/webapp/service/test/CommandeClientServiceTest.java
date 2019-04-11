package ma.pfa.webapp.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.IClientDao;
import ma.pfa.webapp.dao.IProduitDao;
import ma.pfa.webapp.model.Client;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.Panier;
import ma.pfa.webapp.model.Produit;
import ma.pfa.webapp.service.CommandeClientService;
import ma.pfa.webapp.service.ICommandeClientService;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class CommandeClientServiceTest {

	@Autowired
	private IProduitDao prodDao;
	
	
	@Autowired
	private ICommandeClientService cmdService;
	
	@Test
	void test() {

		Produit prod1 = prodDao.findById(prodDao.save(new Produit("Prod A",100)));
		Produit prod2 = prodDao.findById(prodDao.save(new Produit("Prod B",120)));
		
		Panier panier = new Panier();
		panier.addItem(prod1, 5);
		panier.addItem(prod1, 2);
		panier.addItem(prod2, 3);
		
		Client cl = new Client();
		cl.setNom("client 101");
		
		CommandeClient cmd = cmdService.saveCommande(panier, cl);
		
		assertEquals("client 101",cmd.getClient().getNom());
		assertEquals(2,cmd.getLigneCommandes().size());
		
		assertEquals(1060,cmdService.getPrixTotal(cmd.getId()));
		
	}

}
