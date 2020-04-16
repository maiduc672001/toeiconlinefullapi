package vn.ducmai.core.serviceimpl;

import vn.ducmai.core.dto.ExerciseListenDTO;
import vn.ducmai.core.persistence.entity.ExerciseListenEntity;
import vn.ducmai.core.service.ExerciseListenService;
import vn.ducmai.core.utils.ExerciseListenBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseListenServiceImpl implements ExerciseListenService {
    @Override
    public Object[] findExerciseByProperties(Map<String, Object> mapProperties, String sortDirection, String sortExpression, Integer first, Integer limit) {
        Object[] objects=SingletonDao.getExerciseListenDaoIns().findByProperties(mapProperties,sortDirection,sortExpression,first,limit);
        List<ExerciseListenDTO> dtos=new ArrayList<ExerciseListenDTO>();
        for (ExerciseListenEntity item:(List<ExerciseListenEntity>)objects[1]) {
            dtos.add(ExerciseListenBeanUtil.entity2Dto(item));
        }
        objects[1]=dtos;
        return objects;
    }

    @Override
    public ExerciseListenDTO findExerciseListenUnique(Integer id) {
        ExerciseListenEntity entity=SingletonDao.getExerciseListenDaoIns().findById(id);
        return ExerciseListenBeanUtil.entity2Dto(entity);
    }

    @Override
    public void saveExeListen(ExerciseListenDTO dto) {
        ExerciseListenEntity entity=ExerciseListenBeanUtil.dto2Entity(dto);
        SingletonDao.getExerciseListenDaoIns().save(entity);
    }

    @Override
    public Integer deleteExerciseListen(List<Integer> ids) {
        return SingletonDao.getExerciseListenDaoIns().delete(ids);
    }
}
