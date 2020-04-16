package vn.ducmai.core.service;


import vn.ducmai.core.dto.ExerciseListenDTO;

import java.util.List;
import java.util.Map;

public interface ExerciseListenService  {
    Object[] findExerciseByProperties(Map<String, Object> mapProperties, String sortDirection, String sortExpression, Integer first, Integer limit);
    ExerciseListenDTO findExerciseListenUnique(Integer id);
    void saveExeListen(ExerciseListenDTO dto);
    Integer deleteExerciseListen(List<Integer> ids);
}
