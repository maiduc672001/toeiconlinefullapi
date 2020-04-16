package vn.ducmai.core.serviceimpl;

import vn.ducmai.core.dto.ExerciseListenDTO;
import vn.ducmai.core.dto.ListenGuidelineDTO;
import vn.ducmai.core.persistence.entity.ExerciseListenEntity;
import vn.ducmai.core.persistence.entity.ListenGuidelineEntity;
import vn.ducmai.core.service.ListenGuidelineService;
import vn.ducmai.core.utils.ExerciseListenBeanUtil;
import vn.ducmai.core.utils.ListenGuidelineBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {
    @Override
    public ListenGuidelineDTO findListenGuidelineById(Integer id) {
        ListenGuidelineEntity entity=SingletonDao.getListenGuidelineDaoInstance().findById(id);
        ListenGuidelineDTO dto=ListenGuidelineBeanUtil.entityToDTO(entity);
        return dto;
    }

    @Override
    public void saveListenGuideline(ListenGuidelineDTO dto) {
        ListenGuidelineEntity entity=ListenGuidelineBeanUtil.dTOTOEntity(dto);
        Timestamp createdDate=new Timestamp(System.currentTimeMillis());
        entity.setCreatedDate(createdDate);
        entity=SingletonDao.getListenGuidelineDaoInstance().save(entity);
    }

    @Override
    public ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO dto) {
        ListenGuidelineEntity entity=ListenGuidelineBeanUtil.dTOTOEntity(dto);
        entity=SingletonDao.getListenGuidelineDaoInstance().update(entity);
        return ListenGuidelineBeanUtil.entityToDTO(entity);
    }

    @Override
    public Integer deleteListenGuideline(List<Integer> ids) {
        return SingletonDao.getListenGuidelineDaoInstance().delete(ids);
    }

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

    @Override
    public ListenGuidelineDTO findListenGuidelineByExciseId(Integer id) {
        ExerciseListenEntity exerciseListenEntity=SingletonDao.getExerciseListenDaoIns().findEqualUnique("exerciseListenId",id);
        ListenGuidelineEntity listenGuidelineEntity=SingletonDao.getListenGuidelineDaoInstance().findEqualUnique("exerciseListenEntity",exerciseListenEntity);
        return ListenGuidelineBeanUtil.entityToDTO(listenGuidelineEntity);
    }
}
