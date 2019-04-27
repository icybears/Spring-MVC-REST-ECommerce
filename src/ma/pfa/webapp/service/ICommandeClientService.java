package ma.pfa.webapp.service;

import java.util.List;
import java.util.Set;

import ma.pfa.webapp.model.Client;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.LigneCommande;
import ma.pfa.webapp.model.Panier;

public interface ICommandeClientService {
	
	
	double getPrixTotal(int idCommande);
	
	CommandeClient saveCommande(Panier panier, Client c);

	CommandeClient getById(int idEntity);

	List<CommandeClient> getAll();

	CommandeClient edit(CommandeClient cmd);

	void remove(CommandeClient cmd);

	Set<LigneCommande> getAllLigneCommande(int idCmd);

	int add(CommandeClient cmd);
	
}
