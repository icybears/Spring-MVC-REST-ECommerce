package ma.pfa.webapp.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.LigneCommande;

@Repository
public class CommandeClientDaoImpl extends CrudGenericDaoImpl<CommandeClient> implements ICommandeClientDao{

	@Override
	public Set<LigneCommande> getLigneCommandes(int idCommande) {
		
		String query = "FROM LigneCommande as lc WHERE lc.pk.commandeClient.id = :id_commande";
		Session session = this.getCurrentSession();
		
		Set<LigneCommande> ligneCommandes =
				new HashSet<LigneCommande>(session.createQuery(query).setParameter("id_commande", idCommande).list());
		return ligneCommandes;
		
	}

	@Override
	public Set<CommandeClient> getCommandeClientByEtat(int idEtat) {
		String query ="FROM CommandeClient as cc WHERE cc.etat.id = :id";
		
		Session session = this.getCurrentSession();
		
		return new HashSet<CommandeClient>(session.createQuery(query).setParameter("id", idEtat).list());
	}
	
	

}
