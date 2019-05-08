package ma.pfa.webapp.dao;

import java.util.Optional;

import ma.pfa.webapp.model.User; 

public interface IUserDao extends ICrudGenericDao<User>{

	User findByUsername(String username);
    User findById(int id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
