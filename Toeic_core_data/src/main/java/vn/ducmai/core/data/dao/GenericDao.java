package vn.ducmai.core.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<ID extends Serializable,T> {
    List<T> findAll();
    T save(T entity);
    T update(T entity);
    Integer delete(List<ID> ids);
    T findById(ID id);
    T findEqualUnique(String property,Object value);
    Object[] findByProperties(Map<String,Object> mapProperties,String sortDirection,String sortExpression,Integer first,Integer limit);

}
