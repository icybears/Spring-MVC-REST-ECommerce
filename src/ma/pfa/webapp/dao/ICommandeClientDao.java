package ma.pfa.webapp.dao;

import java.util.Set;

import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.LigneCommande;

public interface ICommandeClientDao extends ICrudGenericDao<CommandeClient>{
	
	Set<LigneCommande> getLigneCommandes(int idCommande);
	
}