package com.lin.myzone.daoImpl;

import com.lin.myzone.beans.PageInfo;
import com.lin.myzone.dao.DAO;


import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("daoImpl")
@Transactional
public class DaoImpl<T> implements DAO<T> {

	@PersistenceContext EntityManager em;
	@Resource JdbcTemplate simpleJdbcTemplate;
	
	public void sava(T t) {
		em.persist(t);
	}
	
	public  void update(T t){
		try{
			em.merge(t);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Class<T> t,Object id){
		em.remove(em.getReference(t, id));
	}
	
	public void clear() {
		
		em.clear();
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public T queryByCod(Class<T> t, String condition) {
		String jpql = "select o from "+t.getSimpleName()+" o where 1=1"+condition;
		T obj = null;
		try{
			obj = (T)em.createQuery(jpql).setFirstResult(0).setMaxResults(1).getSingleResult();
		}catch (Exception e) {
			return null;
		}
		em.close();
		return obj;
	}

	public List<T> queryListAllBySql(Class<T> t,String sql) {
		return simpleJdbcTemplate.queryForList(sql);
	}
	

	/**
	 * 分页查询
	 * @param t
	 * @param jpql，查询语句
	 * @param pageInfo，分页信息
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<T> queryList4Page(Class<T> t,String condition,PageInfo pageInfo){	
		String jpql="select o from "+t.getSimpleName()+" o where 1=1 "+condition;
		if (pageInfo==null) {
			return em.createQuery(jpql).getResultList();
		}
		Long count =(Long)em.createQuery("select count(o) from "+t.getSimpleName()+" o where 1=1 "+condition).getSingleResult();
		pageInfo.setPageTotal(count.intValue());	
		return em.createQuery(jpql).setFirstResult(pageInfo.getFirstRecordIndex())
				.setMaxResults(pageInfo.getPageMax())
				.getResultList();		
	}

	public List<T> queryListAll(Class<T> t, String condition) {
		String jpql="select o from "+t.getSimpleName()+" o where 1=1 "+condition;
			return em.createQuery(jpql).getResultList();

	}

	public T queryOne(Class<T> t, Object id) {
		T tt=em.find(t, id);
		em.clear();
		return tt;
	}


	public void removeEntity(T t) {
		em.remove(t);
		
	}


	public void deleteEntityBysql(String sql) {
		simpleJdbcTemplate.update(sql);
		
	}


	public void removePersistentEntity(T t) {
		em.remove(em.merge(t));
		
	}
	
	
/**
 * test
 */
	public Object test() {
		//String jpql = "select u.friendUser.id from FriendMaintain u where u.status=1 and u.selfUser.id=1";
		String jpql = "select o from User o where 1=1 and o.id in(select u.friendUser.id from FriendMaintain u where u.status=1 and u.selfUser.id=1)";
		
		Object obj = null;
		try{
			obj = (T)em.createQuery(jpql).setFirstResult(0).setMaxResults(1).getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return obj;
	}
}
