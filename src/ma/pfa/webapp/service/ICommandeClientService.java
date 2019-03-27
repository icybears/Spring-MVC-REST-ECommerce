package ma.pfa.webapp.service;

import ma.pfa.webapp.model.Client;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.Panier;

public interface ICommandeClientService {
	
	CommandeClient findById(int idEntity);
	
	double getPrixTotal(int idCommande);
	
	void saveCommande(Panier panier, Client c);
	
}
