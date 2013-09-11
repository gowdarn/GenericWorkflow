package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import context.AppContext;

public class DAO  {

	private static IGenericDao genericDao;
	
	public static IGenericDao genericEntityDao(){
		if (genericDao==null){
			genericDao = (IGenericDao) AppContext.getBean("genericDao");
		}
		return genericDao;
	}
	

	public static SessionFactory getSessionFactory(){
		return genericEntityDao().getSessionFactory();
	}
	
	public static void clear() {
		genericEntityDao().clear();
	}

	public static Criteria criteria(Class<?> clazz) {
		return genericEntityDao().criteria(clazz);
	}

	public static void delete(Object entity) {
		genericEntityDao().delete(entity);
	}

	
	public static void deleteByID(Class<?> clazz, int idEntity) {
		genericEntityDao().deleteByID(clazz, idEntity);
	}

	
	public static void deleteNoFlush(Object entity) {
		genericEntityDao().delete(entity);
	}

	
	public static List<?> findAll(Class<?> clazz) {
		return genericEntityDao().findAll(clazz);
	}

	
	public static Object findByID(Class<?> clazz, int idEntity) {
		return genericEntityDao().findByID(clazz, idEntity);
	}
	
	
	public static Object loadByID(Class<?> clazz, int idEntity) {
		return genericEntityDao().loadByID(clazz, idEntity);
	}


	
	public static Object findByID(String className, int idEntity) {
		return genericEntityDao().findByID(className, idEntity);
	}

	
	public static List<?> findByParam(Class<?> clazz, String paramName, Object paramValue) {
		return genericEntityDao().findByParam(clazz, paramName, paramValue);
	}

	
	public static List<?> findListByExample(Class<?> clazz, Object object) {
		return genericEntityDao().findListByExample(clazz, object);
	}

	
	public static Object findUniqueByExample(Class<?> clazz, Object object) {
		return genericEntityDao().findUniqueByExample(clazz, object);
	}

	
	public static Object findUniqueByParam(Class<?> clazz, String paramName, Object paramValue) {
		return genericEntityDao().findUniqueByParam(clazz, paramName, paramValue);
	}

	
	public static void flush() {
		genericEntityDao().flush();
	}

	
	public static Integer insert(Object entity) {
		return genericEntityDao().insert(entity);
	}

	
	public static Object merge(Object entity) {
		return genericEntityDao().merge(entity);
	}

	
	public static void save(Object entity) {
		genericEntityDao().saveOrUpdate(entity);
	}
	
	public static void saveOrUpdate(Object entity) {
		genericEntityDao().saveOrUpdate(entity);
	}

	public static Session currentSession() {
		return genericEntityDao().currentSession();
	}
	
}
