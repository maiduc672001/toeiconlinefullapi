package vn.ducmai.core.dao;

import vn.ducmai.core.data.dao.GenericDao;
import vn.ducmai.core.persistence.entity.UserEntity;

import java.util.List;

public interface UserDao extends GenericDao<Integer, UserEntity>{
    Object[] checkLogin(String name,String password);
    List<UserEntity> findAllByName(List<String> names);
}
