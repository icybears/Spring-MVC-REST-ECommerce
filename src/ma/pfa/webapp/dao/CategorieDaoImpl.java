package ma.pfa.webapp.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ma.pfa.webapp.model.Categorie;
import ma.pfa.webapp.model.Produit;

@Repository
public class CategorieDaoImpl extends CrudGenericDaoImpl<Categorie> implements ICategorieDao {

	@Override
	public Set<Produit> getProduits(int id) {
		Session session = this.getCurrentSession();
		String query = "FROM Produit p WHERE p.categorie.id = :id_categorie";
		return new HashSet<Produit>((session.createQuery(query,Produit.class).setParameter("id_categorie",id).getResultList()));
	}



}
