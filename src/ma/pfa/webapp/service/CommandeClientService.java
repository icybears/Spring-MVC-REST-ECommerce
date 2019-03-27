package ma.pfa.webapp.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.pfa.webapp.dao.ICommandeClientDao;
import ma.pfa.webapp.dao.ILigneCommandeDao;
import ma.pfa.webapp.model.Client;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.LigneCommande;
import ma.pfa.webapp.model.Panier;
@Service
public class CommandeClientService implements ICommandeClientService{

		
		@Autowired
		private ICommandeClientDao commandeClientDao;
		
		@Autowired 
		private ILigneCommandeDao ligneCommandeDao;
		
	    public CommandeClient findById(int idEntity){
	    	return  commandeClientDao.findById(idEntity);
	    }
		
		
		@Override
		public double getPrixTotal(int idCommande) {
		    Set<LigneCommande> ligneCommandes = commandeClientDao.getLigneCommandes(idCommande);
		    //commandeClientDao.
			double total=0;
		    for(LigneCommande lc : ligneCommandes)
		    {
		    	total += lc.getProduit().getPrix()*lc.getQuantite();
		    }
			return total;
		}

		@Override
		public void saveCommande(Panier panier, Client c) {
			CommandeClient commande = new CommandeClient();
			commande.setDateCreation(new Date());
			commande.setClient(c);
			Set<LigneCommande> ligneCommandes = new HashSet<LigneCommande>(
					panier.getItems().values());
			
			commande.setLigneCommandes(ligneCommandes);
			commandeDao.create(commande);
			
			for(LigneCommande lc : ligneCommandes)
			{
				lc.setCommande(commande);
				ligneCommandeDao.create(lc);
				
			}
			
		}

	}

	
