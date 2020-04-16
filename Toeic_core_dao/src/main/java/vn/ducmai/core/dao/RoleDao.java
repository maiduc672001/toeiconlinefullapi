package vn.ducmai.core.dao;

import vn.ducmai.core.data.dao.GenericDao;
import vn.ducmai.core.persistence.entity.RoleEntity;

import java.util.List;

public interface RoleDao extends GenericDao<Integer, RoleEntity> {
    List<RoleEntity> findAllByNames(List<String> names);
}
