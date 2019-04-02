package ma.pfa.webapp.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ma.pfa.webapp.model.Cooperative;
import ma.pfa.webapp.model.Produit;

@Repository
public class CooperativeDaoImpl extends CrudGenericDaoImpl<Cooperative> implements ICooperativeDao{

	
	@Override
	public Set<Produit> getProduits(int id) {
			Session session = this.getCurrentSession();
			String query = "FROM Produit p WHERE p.cooperative.id = :id_coop ";
		return new HashSet<Produit>(session.createQuery(query).setParameter("id_coop", id).getResultList());
		
	}

}
