package ma.pfa.webapp.dao.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.IAvoirDao;
import ma.pfa.webapp.dao.ICommandeClientDao;
import ma.pfa.webapp.dao.IEtatDao;
import ma.pfa.webapp.dao.ILigneCommandeDao;
import ma.pfa.webapp.dao.IProduitDao;
import ma.pfa.webapp.model.Avoir;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.Etat;
import ma.pfa.webapp.model.IdLigneCommande;
import ma.pfa.webapp.model.LigneCommande;
import ma.pfa.webapp.model.Produit;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class CommandeClientDaoImplTest {

	@Autowired
	private ICommandeClientDao comDao;

	@Autowired
	private IEtatDao etDao;

	@Autowired
	private IProduitDao prodDao;
	
	@Autowired
	private ILigneCommandeDao lcDao;

	@Autowired
	private IAvoirDao avoirDao;
	
	@Test
	@Disabled
	void testEtatCommandeClient() {

		Etat etat = new Etat();
		etat.setNom("etat1");

		int idEtat = etDao.save(etat);
		Etat etatEntity = etDao.findById(idEtat);

		CommandeClient com = new CommandeClient();
		com.setEtat(etatEntity);

		int idCom = comDao.save(com);

		assertNotEquals(0, comDao.findAll().size());

		assertEquals("etat1", comDao.findById(idCom).getEtat().getNom());

		etatEntity = etDao.findById(2);

		CommandeClient comEntity = comDao.findById(idCom);
		comEntity.setEtat(etatEntity);
		comEntity = comDao.update(comEntity);

		assertEquals("etat2", comEntity.getEtat().getNom());

	}

	@Test
	@Disabled
	void getCommandeClientByEtat() {
		Etat etat = new Etat();
		etat.setNom("etat Test");

		int idEtat = etDao.save(etat);
		Etat etatEntity = etDao.findById(idEtat);

		CommandeClient com = new CommandeClient();
		com.setEtat(etatEntity);
		comDao.save(com);

		com = new CommandeClient();
		com.setEtat(etatEntity);
		comDao.save(com);

		com = new CommandeClient();
		com.setEtat(etatEntity);
		comDao.save(com);

		assertEquals(3, comDao.getCommandeClientByEtat(idEtat).size());

	}

	@Test
	@Disabled
	void crudCommande() {
		CommandeClient cmd = new CommandeClient();
		int idCmd = comDao.save(cmd);
		CommandeClient cmdEntity = comDao.findById(idCmd);
		
		Produit prodEntity = prodDao.findById(prodDao.save(new Produit("prod 4P30", 540)));
		cmdEntity.addLigneCommande(new LigneCommande(prodEntity, cmdEntity, 11));
		
		prodEntity = prodDao.findById(prodDao.save(new Produit("prod 2DKL", 430)));
		cmdEntity.addLigneCommande(new LigneCommande(prodEntity, cmdEntity, 10));
		

		cmdEntity = comDao.update(cmdEntity);
		
		assertEquals(2, cmdEntity.getLigneCommandes().size());
		
		comDao.delete(cmdEntity);
		
		assertNull(comDao.findById(cmdEntity.getId()));
		assertNull(lcDao.findByIdLigne(new IdLigneCommande(prodEntity,cmdEntity)));
		
	}
	

	@Test
	void testCommandeClientAvoir() {
		// creation de la commande
		CommandeClient cmd = new CommandeClient();
		int idCmd = comDao.save(cmd);
		CommandeClient cmdEntity = comDao.findById(idCmd);
		//creation de lignes de commandes
		Produit prodEntity = prodDao.findById(prodDao.save(new Produit("prod 9304P30", 240)));
		cmdEntity.addLigneCommande(new LigneCommande(prodEntity, cmdEntity, 7));
		
		prodEntity = prodDao.findById(prodDao.save(new Produit("prod 7632D2DKL", 330)));
		cmdEntity.addLigneCommande(new LigneCommande(prodEntity, cmdEntity, 4));
		
		
		cmdEntity = comDao.update(cmdEntity);
		
		LigneCommande lcEntity = lcDao.findByIdLigne(new IdLigneCommande(prodEntity, cmdEntity));
		Avoir avoir = new Avoir(3);
		avoir.addLigneCommande(lcEntity);
		
		avoirDao.save(avoir);
		
		assertEquals(5,lcDao.findByIdLigne(new IdLigneCommande(prodEntity, cmdEntity)).getAvoir().getQuantite());
		
	}

}
