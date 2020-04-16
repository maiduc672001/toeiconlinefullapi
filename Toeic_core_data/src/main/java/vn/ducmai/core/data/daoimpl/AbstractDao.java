package vn.ducmai.core.data.daoimpl;

import org.hibernate.*;

import vn.ducmai.core.common.CoreConstant;
import vn.ducmai.core.data.dao.GenericDao;
import vn.ducmai.core.utils.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private Class<T> persistenceClass;

    public AbstractDao() {
        this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    private String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }

    @Override
    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<T> ts = null;
        try {
            StringBuilder sql = new StringBuilder("FROM ").append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            ts = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return ts;
    }

    @Override
    public T save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public T update(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T t = null;
        try {
            t = (T) session.merge(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return t;
    }

    @Override
    public Integer delete(List<ID> ids) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Integer count = 0;
        try {
            for (ID item : ids) {
                T t2 = (T) session.get(this.persistenceClass, item);
                session.delete(t2);
                count++;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return count;
    }

    @Override
    public T findById(ID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T t = null;
        try {
            t = (T) session.get(this.persistenceClass, id);
            if (t == null) {
                throw new ObjectNotFoundException("NOT FOUND" + id, null);
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return t;
    }

    @Override
    public T findEqualUnique(String property, Object value) {
        T object = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("FROM ");
            sql.append(this.getPersistenceClassName()).append(" model WHERE model." + property + "= :value");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value", value);
            object = (T) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return object;
    }

    @Override
    public Object[] findByProperties(Map<String, Object> mapProperties, String sortDirection, String sortExpression, Integer first, Integer limit) {
        List<T> list;
        Object totalItems = 0;
        String[] params = new String[mapProperties.size()];
        String[] values = new String[mapProperties.size()];
        int i = 0;
        for (Map.Entry item : mapProperties.entrySet()) {
            params[i] = (String) item.getKey();
            values[i] = (String) item.getValue();
            i++;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql1 = new StringBuilder("FROM ");
            sql1.append(this.getPersistenceClassName()).append(" WHERE 1=1 ");
            if (mapProperties.size() > 0) {
                for (int j = 0; j < params.length; j++) {
                    sql1.append(" AND " +"LOWER (" +params[j] + ") LIKE :" + params[j]);
                }
            }
            if (sortDirection != null && sortExpression != null) {
                sql1.append(" ORDER BY ").append(sortExpression);
                sql1.append(" "+(sortExpression.equals(CoreConstant.SORT_ASC)?"asc":"desc"));
            }
            Query query1 = session.createQuery(sql1.toString());
            if (mapProperties.size() > 0) {
                for (int j = 0; j < params.length; j++) {
                    query1.setParameter(params[j],"%"+ values[j]+"%");
                }
            }
            if(first!=null&&first>=0){
query1.setFirstResult(first);
            }
            if(limit!=null&&limit>0){
                query1.setMaxResults(limit);
            }
            list=query1.list();
            StringBuilder sql2=new StringBuilder("SELECT COUNT(*) FROM ").append(this.getPersistenceClassName()).append(" WHERE 1=1 ");
            if(mapProperties.size()>0){
                for(int j=0;j<params.length;j++){
                    sql2.append(" AND "+"LOWER ("+params[j]+") LIKE :"+params[j]);
                }
            }
            Query query2=session.createQuery(sql2.toString());
            if (mapProperties.size() > 0) {
                for (int j = 0; j < params.length; j++) {
                    query2.setParameter(params[j],"%"+ values[j]+"%");
                }
            }
            totalItems= query2.list().get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return new Object[]{totalItems,list};
    }
}
