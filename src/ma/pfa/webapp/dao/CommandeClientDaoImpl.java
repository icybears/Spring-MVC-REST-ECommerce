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
		
		String query = "from LigneCommande as lc where lc.pk.commandeClient.id_commande = :id_commande";
		Session session = this.getCurrentSession();
		
		Set<LigneCommande> ligneCommandes =
				new HashSet<LigneCommande>(session.createQuery(query).setParameter("id_commande", idCommande).list());
		return ligneCommandes;
		
	}

}
