package ma.pfa.webapp.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class CrudGenericDaoImpl<T> implements ICrudGenericDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> type;

//	public void setType( Class<T> type ) {
//		this.type = type;
//	}

	public CrudGenericDaoImpl() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public int save(T instance) {
		 return (Integer) getCurrentSession().save(instance);
	}

	@Override
	public T findById(int id) {
		return (T) getCurrentSession().get(type, id);
	}

	@Override
	public T update(T instance) {
		return (T) getCurrentSession().merge(instance);
	}

	@Override
	public void delete(T instance) {
		getCurrentSession().delete(instance);
	}

	@Override
	public List<T> findAll() {

		return getCurrentSession().createQuery("FROM " + type.getSimpleName(), type).getResultList();
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
