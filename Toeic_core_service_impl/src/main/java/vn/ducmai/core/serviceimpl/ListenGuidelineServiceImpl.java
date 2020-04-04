package vn.ducmai.core.serviceimpl;

import vn.ducmai.core.dto.ListenGuidelineDTO;
import vn.ducmai.core.persistence.entity.ListenGuidelineEntity;
import vn.ducmai.core.service.ListenGuidelineService;
import vn.ducmai.core.utils.ListenGuidelineBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {
    @Override
    public Object[] findListenGuidelineByProperties(Map<String, Object> mapProperties, String sortDirection, String sortExpression, Integer first, Integer limit) {
        Object[] objects=SingletonDao.getListenGuidelineDaoInstance().findByProperties(mapProperties,sortDirection,sortExpression,first,limit);
        List<ListenGuidelineEntity> entities= (List<ListenGuidelineEntity>) objects[1];
        List<ListenGuidelineDTO> dtos=new ArrayList<ListenGuidelineDTO>();
        for (ListenGuidelineEntity entity:entities) {
            ListenGuidelineDTO dto= ListenGuidelineBeanUtil.entityToDTO(entity);
            dtos.add(dto);
        }
        objects[1]=dtos;
        return objects;
    }
}
