package vn.ducmai.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ExerciseListenDTO implements Serializable {
    private Integer exerciseListenId;
    private String name;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    public Integer getExerciseListenId() {
        return exerciseListenId;
    }

    public void setExerciseListenId(Integer exerciseListenId) {
        this.exerciseListenId = exerciseListenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<ExerciseListenQuestionDTO> getExerciseListenQuestionDTOS() {
        return exerciseListenQuestionDTOS;
    }

    public void setExerciseListenQuestionDTOS(List<ExerciseListenQuestionDTO> exerciseListenQuestionDTOS) {
        this.exerciseListenQuestionDTOS = exerciseListenQuestionDTOS;
    }

    private List<ExerciseListenQuestionDTO> exerciseListenQuestionDTOS;
}
