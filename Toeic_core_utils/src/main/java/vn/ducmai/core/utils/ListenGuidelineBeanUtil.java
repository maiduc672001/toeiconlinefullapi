package vn.ducmai.core.utils;

import vn.ducmai.core.dto.ListenGuidelineDTO;
import vn.ducmai.core.persistence.entity.ListenGuidelineEntity;

public class ListenGuidelineBeanUtil {
    public static ListenGuidelineDTO entityToDTO(ListenGuidelineEntity entity) {
        ListenGuidelineDTO dto=new ListenGuidelineDTO();
        dto.setListenGuidelineId(entity.getListenGuidelineId());
        dto.setContent(entity.getContent());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setExerciseListenDTO(ExerciseListenBeanUtil.entity2Dto(entity.getExerciseListenEntity()));
        return dto;
    }
    public static ListenGuidelineEntity dTOTOEntity(ListenGuidelineDTO dto){
        ListenGuidelineEntity entity=new ListenGuidelineEntity();
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setImage(dto.getImage());
        entity.setListenGuidelineId(dto.getListenGuidelineId());
        entity.setTitle(dto.getTitle());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setExerciseListenEntity(ExerciseListenBeanUtil.dto2Entity(dto.getExerciseListenDTO()));
        return entity;
    }
}
