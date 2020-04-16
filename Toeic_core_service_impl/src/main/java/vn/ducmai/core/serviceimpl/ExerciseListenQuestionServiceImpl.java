package vn.ducmai.core.serviceimpl;

import vn.ducmai.core.dto.ExerciseListenQuestionDTO;
import vn.ducmai.core.persistence.entity.ExerciseListenEntity;
import vn.ducmai.core.persistence.entity.ExerciseQuestionListenEntity;
import vn.ducmai.core.service.ExerciseListenQuestionService;
import vn.ducmai.core.utils.ExerciseListenQuestionBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseListenQuestionServiceImpl implements ExerciseListenQuestionService {

    @Override
    public Object[] findExerciseQuestionServiceByProperties(Map<String, Object> mapProperties, String sortDirection, String sortExpression, Integer first, Integer limit) {
        Object[] objects= SingletonDao.getExerciseListenQuestionDaoIns().findByProperties(mapProperties,sortDirection,sortExpression,first,limit);
        List<ExerciseListenQuestionDTO> dtos=new ArrayList<ExerciseListenQuestionDTO>();
        for (ExerciseQuestionListenEntity item:(List<ExerciseQuestionListenEntity>)objects[1]) {
            dtos.add(ExerciseListenQuestionBeanUtil.entity2Dto(item));
        }
        objects[1]=dtos;
        return objects;
    }

    @Override
    public ExerciseListenQuestionDTO findQuestionById(Integer id) {
        ExerciseQuestionListenEntity entity=SingletonDao.getExerciseListenQuestionDaoIns().findById(id);
        return ExerciseListenQuestionBeanUtil.entity2Dto(entity);
    }

    @Override
    public void saveExerciseListenQuestion(ExerciseListenQuestionDTO dto) {
        ExerciseQuestionListenEntity entity=ExerciseListenQuestionBeanUtil.dto2Entity(dto);
        SingletonDao.getExerciseListenQuestionDaoIns().save(entity);
    }

    @Override
    public void updateExerciseListenQuestion(ExerciseListenQuestionDTO dto) {
        ExerciseQuestionListenEntity entity=ExerciseListenQuestionBeanUtil.dto2Entity(dto);
        SingletonDao.getExerciseListenQuestionDaoIns().update(entity);
    }

    @Override
    public Integer deleteExerciseListenQuestion(List<Integer> ids) {
        return SingletonDao.getExerciseListenQuestionDaoIns().delete(ids);
    }


}
