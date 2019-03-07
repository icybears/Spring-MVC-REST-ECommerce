package ma.pfa.webapp.dao;

import java.util.List;

public interface ICrudGenericDao<T> {

		int save(T instance);
		
		T findById(int id);
		
		void update(T instance);
		
		void delete(T instance);
		
		List<T> findAll();
	}


