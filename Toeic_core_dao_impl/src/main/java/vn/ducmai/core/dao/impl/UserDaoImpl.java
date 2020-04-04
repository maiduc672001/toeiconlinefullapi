package vn.ducmai.core.dao.impl;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.ducmai.core.dao.UserDao;
import vn.ducmai.core.data.daoimpl.AbstractDao;
import vn.ducmai.core.persistence.entity.UserEntity;
import vn.ducmai.core.utils.HibernateUtil;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

    @Override
    public Object[] checkLogin(String name, String password) {
        boolean check=false;
        String roleName=null;
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            StringBuilder sql=new StringBuilder("FROM UserEntity ue WHERE ue.name= :name AND ue.password= :password");
            Query query=session.createQuery(sql.toString());
            query.setParameter("name",name);
            query.setParameter("password",password);
            UserEntity entity= (UserEntity) query.uniqueResult();
            if(entity!=null){
                check=true;
                roleName=entity.getRoleEntity().getName();
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }
        return new Object[]{check,roleName};
    }
}
