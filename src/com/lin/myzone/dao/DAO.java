package com.lin.myzone.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lin.myzone.beans.PageInfo;


public interface DAO<T> {
	/**
	 * 保存实体
	 * @param t
	 */
		public void sava(T t);
		
		/**
		 * 更新实体
		 * @param t
		 */
		public void update(T t);
		/**
		 * 清除一级缓存的数据
		 */
		public void clear();
		/**
		 * 删除实体
		 */
		public void delete(Class<T> t,Object id); 		
		
		
		/**
		 * 查询一个实体
		 * @param t
		 * @param id
		 * @return
		 */
		@Transactional(propagation=Propagation.NOT_SUPPORTED)
		public T queryOne(Class<T> t,Object id);
		/**
		 * 根据实际情况查询
		 * @param t
		 * @param condition
		 * @return
		 */
		public T queryByCod(Class<T> t,String condition);
		
		/**
		 * 查询所有符合条件
		 * @param t
		 * @param condition
		 * @return
		 */
		public List<T> queryListAll(Class<T> t,String condition);
		
		/**
		 * 根据单独的sql查询
		 * @param t
		 * @param sql
		 * @return
		 */
		public List<T> queryListAllBySql(Class<T> t,String sql);
		
		/**
		 * 分页查询
		 * @param t
		 * @param jpql，查询语句
		 * @param pageInfo，分页信息
		 * @return
		 */
		public List<T> queryList4Page(Class<T> t,String condition,PageInfo pageInfo);
		
		/**
		 * 移除一个实体
		 * @param t
		 */
		public void removeEntity(T t);
		
		
		/**
		 * 根据主键删除数据
		 * @param sql
		 * @param id
		 */
		public void deleteEntityBysql(String sql);
		
		
		/**
		 * 移除一个已经持久并已经session失效的实体
		 * @param t
		 */
		public void removePersistentEntity(T t);

		
		public Object test();
}
