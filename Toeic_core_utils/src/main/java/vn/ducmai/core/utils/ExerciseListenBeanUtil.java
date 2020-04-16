package vn.ducmai.core.utils;

import vn.ducmai.core.dto.ExerciseListenDTO;
import vn.ducmai.core.persistence.entity.ExerciseListenEntity;

public class ExerciseListenBeanUtil {
    public static ExerciseListenDTO entity2Dto(ExerciseListenEntity entity) {
        ExerciseListenDTO dto = new ExerciseListenDTO();
        dto.setExerciseListenId(entity.getExerciseListenId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }
    public static ExerciseListenEntity dto2Entity(ExerciseListenDTO dto) {
        ExerciseListenEntity entity = new ExerciseListenEntity();
        entity.setExerciseListenId(dto.getExerciseListenId());
        entity.setName(dto.getName());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        return entity;
    }
}
