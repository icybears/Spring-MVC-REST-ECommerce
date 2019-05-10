package ma.pfa.webapp.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ma.pfa.webapp.model.Role;
import ma.pfa.webapp.model.RoleName;




@Repository
public class RoleDaoImpl extends CrudGenericDaoImpl<Role> implements IRoleDao{

	@Override
	public Role findByName(RoleName roleName) {
		Session session = this.getCurrentSession();
		String query = "FROM Role r WHERE r.name = :rolename";
		
		List<Role> results =session.createQuery(query,Role.class).setParameter("rolename",roleName).getResultList();
		Role role = null;
		if(!results.isEmpty()){
		    // ignores multiple results
		    role = results.get(0);
		}
		
		return role;
	}

}
