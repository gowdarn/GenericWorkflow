package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

public class GenericDao implements IGenericDao {

	protected SessionFactory sessionFactory;

	@Override
	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}

	@Override
	public Criteria criteria(Class<?> clazz) {
		return sessionFactory.getCurrentSession().createCriteria(clazz);
	}

	public Criteria criteria(String className) {
		return sessionFactory.getCurrentSession().createCriteria(className);
	}

	@Override
	public Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void delete(Object entity) {
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteByID(Class<?> clazz, int idEntity) {
		final Object entity = sessionFactory.getCurrentSession().get(clazz, idEntity);
		delete(entity);
	}

	@Override
	public void deleteNoFlush(Object entity) {
		sessionFactory.getCurrentSession().delete(entity);
		delete(entity);
	}

	@Override
	public List<?> findAll(Class<?> clazz) {
		return criteria(clazz).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public Object findByID(Class<?> clazz, int idEntity) {
		return sessionFactory.getCurrentSession().get(clazz, idEntity);
	}
	
	@Override
	public Object loadByID(Class<?> clazz, int idEntity) {
		return sessionFactory.getCurrentSession().load(clazz, idEntity);
	}


	@Override
	public Object findByID(String className, int idEntity) {
		return sessionFactory.getCurrentSession().get(className, idEntity);
	}

	@Override
	public List<?> findByParam(Class<?> clazz, String paramName, Object paramValue) {
		return currentSession().createCriteria(clazz).add(Restrictions.eq(paramName, paramValue)).list();
	}

	@Override
	public List<?> findListByExample(Class<?> clazz, Object object) {
		return currentSession().createCriteria(clazz).add(Example.create(object)).list();
	}

	@Override
	public Object findUniqueByExample(Class<?> clazz, Object object) {
		return currentSession().createCriteria(clazz).add(Example.create(object)).uniqueResult();
	}

	@Override
	public Object findUniqueByParam(Class<?> clazz, String paramName, Object paramValue) {
		return currentSession().createCriteria(clazz).add(Restrictions.eq(paramName, paramValue)).uniqueResult();
	}

	@Override
	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public Integer insert(Object entity) {
		final Integer id = (Integer) sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		return id;
	}

	@Override
	public Object merge(Object entity) {
		final Object obj = sessionFactory.getCurrentSession().merge(entity);
		sessionFactory.getCurrentSession().flush();
		return obj;
	}

	@Override
	public void save(Object entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
