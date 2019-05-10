package ma.pfa.webapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import ma.pfa.webapp.model.Client;


@Repository
public class ClientDaoImpl extends CrudGenericDaoImpl<Client> implements IClientDao {

	@Override
	public Client getClientByUserId(int id) {
		
		Session session = this.getCurrentSession();
		String query = "FROM Client c WHERE c.user.id = :id";
		
		List<Client> results =session.createQuery(query,Client.class).setParameter("id",id).getResultList();
		Client client = null;
		if(!results.isEmpty()){
		    
		    client = results.get(0); 
		    
		}
		return client;
	}

	

}
