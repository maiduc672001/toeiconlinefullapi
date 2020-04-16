package vn.ducmai.web.logic.command;

import vn.ducmai.core.dto.ExerciseListenDTO;
import vn.ducmai.core.persistence.entity.ExerciseListenEntity;
import vn.ducmai.core.web.command.AbstractCommand;

import java.util.List;

public class ExerciseListenCommand extends AbstractCommand<ExerciseListenDTO> {
    public ExerciseListenCommand(){
        this.pojo=new ExerciseListenDTO();
    }
    private String name;
private List<Integer> ids;
private Integer exerciseListenId;
private int totalPages;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getExerciseListenId() {
        return exerciseListenId;
    }

    public void setExerciseListenId(Integer exerciseListenId) {
        this.exerciseListenId = exerciseListenId;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
