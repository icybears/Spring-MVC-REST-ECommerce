package ma.pfa.webapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.IClientDao;
import ma.pfa.webapp.dao.ICommandeClientDao;
import ma.pfa.webapp.dao.ILigneCommandeDao;
import ma.pfa.webapp.model.Client;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.LigneCommande;
import ma.pfa.webapp.model.Panier;

@Service
@Transactional
public class CommandeClientService implements ICommandeClientService{

		
		@Autowired
		private ICommandeClientDao cmdDao;
		
		
		@Autowired
		private IClientDao clDao;
		
	    public CommandeClient findById(int idEntity){
	    	return  cmdDao.findById(idEntity);
	    }
		
		
		@Override
		public double getPrixTotal(int idCommande) {
		    Set<LigneCommande> ligneCommandes = cmdDao.getLigneCommandes(idCommande);
		    //commandeClientDao.
			double total=0;
		    for(LigneCommande lc : ligneCommandes)
		    {
		    	total += lc.getProduit().getPrix()*lc.getQuantite();
		    }
			return total;
		}

		@Override
		public CommandeClient saveCommande(Panier panier, Client c) {
			
	
			int clId = clDao.save(c);
			
			CommandeClient commande = new CommandeClient();

			commande.setClient(clDao.findById(clId));
			
			Set<LigneCommande> ligneCommandes = new HashSet<LigneCommande>(
					panier.getItems().values());
			
			commande.setLigneCommandes(ligneCommandes);
			for(LigneCommande lc : ligneCommandes)
			{
				lc.setCommandeClient(commande);
				
			}
			
			int idCmd = cmdDao.save(commande);		

			
			return cmdDao.findById(idCmd);
			
		}

	}

	
