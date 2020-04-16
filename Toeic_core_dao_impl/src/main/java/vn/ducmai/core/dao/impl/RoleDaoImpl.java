package vn.ducmai.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.ducmai.core.dao.RoleDao;
import vn.ducmai.core.data.daoimpl.AbstractDao;
import vn.ducmai.core.persistence.entity.RoleEntity;
import vn.ducmai.core.utils.HibernateUtil;

import java.util.List;

public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao {
    @Override
    public List<RoleEntity> findAllByNames(List<String> names) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        List<RoleEntity> entities=null;
        try {
            StringBuilder sql=new StringBuilder("FROM RoleEntity re WHERE re.name IN (:names)");
            Query query=session.createQuery(sql.toString());
            query.setParameterList("names",names);
            entities=query.list();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return entities;
    }
}
