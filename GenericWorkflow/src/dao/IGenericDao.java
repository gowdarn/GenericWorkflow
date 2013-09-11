package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface IGenericDao {

	public void clear();

	public Criteria criteria(Class<?> clazz);

	public Session currentSession();

	public void delete(Object entity);

	public void deleteByID(Class<?> clazz, int idEntity);

	void deleteNoFlush(Object entity);

	public List<?> findAll(Class<?> clazz);

	public Object findByID(Class<?> clazz, int idEntity);

	Object findByID(String className, int idEntity);

	List<?> findByParam(Class<?> clazz, String paramName, Object paramValue);

	public List<?> findListByExample(Class<?> clazz, Object object);

	Object findUniqueByExample(Class<?> clazz, Object object);

	Object findUniqueByParam(Class<?> clazz, String paramName, Object paramValue);

	public void flush();

	public SessionFactory getSessionFactory();

	public Integer insert(Object entity);

	public Object merge(Object entity);

	public void save(Object entity);

	public void saveOrUpdate(Object entity);

	public void setSessionFactory(SessionFactory sessionFactory);

	Object loadByID(Class<?> clazz, int idEntity);

}
