package vn.ducmai.core.dao;

import vn.ducmai.core.data.dao.GenericDao;
import vn.ducmai.core.persistence.entity.ListenGuidelineEntity;

import java.util.Map;

public interface ListenGuidelineDao extends GenericDao<Integer, ListenGuidelineEntity> {
    //Object[] findListenGuidelineByProperties(Map<String,Object> properties,String sortDirection,String sortExpression,Integer first,Integer limit);
}
