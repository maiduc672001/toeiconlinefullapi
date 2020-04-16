package vn.ducmai.web.logic.command;

import vn.ducmai.core.dto.ExerciseListenQuestionDTO;
import vn.ducmai.core.web.command.AbstractCommand;

import java.util.List;

public class ExerciseListenQuestionCommand extends AbstractCommand<ExerciseListenQuestionDTO> {
    public ExerciseListenQuestionCommand(){
        this.pojo=new ExerciseListenQuestionDTO();
    }
    private Integer exerciseListenId;
private Integer exerciseListenQuestionId;

private Integer totalPages;
private Integer answerUser;
private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Integer getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(Integer answerUser) {
        this.answerUser = answerUser;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    private List<Integer> ids;
    public Integer getExerciseListenQuestionId() {
        return exerciseListenQuestionId;
    }

    public void setExerciseListenQuestionId(Integer exerciseListenQuestionId) {
        this.exerciseListenQuestionId = exerciseListenQuestionId;
    }

    public Integer getExerciseListenId() {
        return exerciseListenId;
    }
    public void setExerciseListenId(Integer exerciseListenId) {
        this.exerciseListenId = exerciseListenId;
    }
}
