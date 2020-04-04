package vn.ducmai.core.dao;

import vn.ducmai.core.data.dao.GenericDao;
import vn.ducmai.core.persistence.entity.UserEntity;

public interface UserDao extends GenericDao<Integer, UserEntity>{
    Object[] checkLogin(String name,String password);

}
