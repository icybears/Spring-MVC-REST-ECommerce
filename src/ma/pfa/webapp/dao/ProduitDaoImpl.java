package ma.pfa.webapp.dao;



import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ma.pfa.webapp.model.Produit;

@Repository
public class ProduitDaoImpl extends CrudGenericDaoImpl<Produit> implements IProduitDao {

	@Override
	public Set<Produit> getProduitsByMatierePremiere(int id) {
		Session session = this.getCurrentSession();
		String query = "SELECT p FROM Produit p JOIN p.matieresPremieres mp WHERE mp.id = :id_mp";
		return new HashSet<Produit>((session.createQuery(query,Produit.class).setParameter("id_mp",id).getResultList()));
	}

}
