package ma.pfa.webapp.dao;

import java.util.Optional;

import ma.pfa.webapp.model.Role;
import ma.pfa.webapp.model.RoleName;


public interface IRoleDao extends ICrudGenericDao<Role>{
	Optional<Role> findByName(RoleName roleName);
}
