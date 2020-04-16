package vn.ducmai.core.service;

import vn.ducmai.core.dto.ExerciseListenQuestionDTO;

import java.util.List;
import java.util.Map;

public interface ExerciseListenQuestionService {
    Object[] findExerciseQuestionServiceByProperties(Map<String, Object> mapProperties, String sortDirection, String sortExpression, Integer first, Integer limit);
    ExerciseListenQuestionDTO findQuestionById(Integer id);
    void saveExerciseListenQuestion(ExerciseListenQuestionDTO dto);
    void updateExerciseListenQuestion(ExerciseListenQuestionDTO dto);
    Integer deleteExerciseListenQuestion(List<Integer> ids);
}
