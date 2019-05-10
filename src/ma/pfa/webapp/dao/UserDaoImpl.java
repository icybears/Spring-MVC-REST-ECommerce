package ma.pfa.webapp.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ma.pfa.webapp.model.User;

@Repository
public class UserDaoImpl extends CrudGenericDaoImpl<User> implements IUserDao{

	@Override
	public User findByUsername(String username) {
		Session session = this.getCurrentSession();
		String query = "FROM User u WHERE u.username = :username";
		
		List<User> results =session.createQuery(query,User.class).setParameter("username",username).getResultList();
		User user = null;
		if(!results.isEmpty()){
		    
		    user = results.get(0);
		    System.out.println("user found: "+user.getUsername());
		}
		
		
		
		return user;
		
	}

	@Override
	public User findById(int id) {
		Session session = this.getCurrentSession();
		String query = "FROM User u WHERE u.id = :id";
		
		List<User> results =session.createQuery(query,User.class).setParameter("id",id).getResultList();
		User user = null;
		if(!results.isEmpty()){
		    
		    user = results.get(0); 
		    System.out.println("user is: "+user.getUsername());
		}
		return user;
	}

	@Override
	public Boolean existsByUsername(String username) {
		Session session = this.getCurrentSession();
		String query = "FROM User u WHERE u.username = :username";
		
		return session.createQuery(query,User.class).setParameter("username",username).getResultList().size() == 1;
	}

	@Override
	public Boolean existsByEmail(String email) {
		Session session = this.getCurrentSession();
		String query = "FROM User u WHERE u.email = :email";
		
		return session.createQuery(query,User.class).setParameter("email",email).getResultList().size() == 1;
	}

}
