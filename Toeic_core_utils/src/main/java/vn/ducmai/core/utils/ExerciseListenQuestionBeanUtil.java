package vn.ducmai.core.utils;

import vn.ducmai.core.dto.ExerciseListenQuestionDTO;
import vn.ducmai.core.persistence.entity.ExerciseQuestionListenEntity;

public class ExerciseListenQuestionBeanUtil {
    public static ExerciseListenQuestionDTO entity2Dto(ExerciseQuestionListenEntity entity) {
        ExerciseListenQuestionDTO dto = new ExerciseListenQuestionDTO();
        dto.setExerciseListenQuestionId(entity.getExerciseQuestionListenId());
        dto.setAudio(entity.getAudio());
        dto.setImage(entity.getImage());
        dto.setCorrectAnswer(entity.getCorrectAnswer());
        dto.setQuestion(entity.getQuestion());
        dto.setOption1(entity.getOption1());
        dto.setOption2(entity.getOption2());
        dto.setOption3(entity.getOption3());
        dto.setOption4(entity.getOption4());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setExerciseListenDTO(ExerciseListenBeanUtil.entity2Dto(entity.getExerciseListenEntity()));
        return dto;
    }
    public static ExerciseQuestionListenEntity dto2Entity(ExerciseListenQuestionDTO dto) {
        ExerciseQuestionListenEntity entity = new ExerciseQuestionListenEntity();
        entity.setExerciseQuestionListenId(dto.getExerciseListenQuestionId());
        entity.setAudio(dto.getAudio());
        entity.setImage(dto.getImage());
        entity.setCorrectAnswer(dto.getCorrectAnswer());
        entity.setQuestion(dto.getQuestion());
        entity.setOption1(dto.getOption1());
        entity.setOption2(dto.getOption2());
        entity.setOption3(dto.getOption3());
        entity.setOption4(dto.getOption4());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setExerciseListenEntity(ExerciseListenBeanUtil.dto2Entity(dto.getExerciseListenDTO()));
        return entity;
    }
}
