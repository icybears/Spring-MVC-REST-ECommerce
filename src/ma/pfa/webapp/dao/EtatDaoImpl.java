package ma.pfa.webapp.dao;


import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ma.pfa.webapp.model.Etat;


@Repository
public class EtatDaoImpl extends CrudGenericDaoImpl<Etat> implements IEtatDao{

	public Etat findByNom(String etat) {
		Session session = this.getCurrentSession();
		String query = "FROM Etat e WHERE e.nom LIKE :etat";
		List<Etat> result = session.createQuery(query,Etat.class).setParameter("etat","%"+etat+"%").getResultList();
		
		Etat et = null;
		if(!result.isEmpty()) {
			et = result.get(0);
		}
		
		return et;
	}
	
}
