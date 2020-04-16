package vn.ducmai.web.logic.command;

import vn.ducmai.core.dto.ListenGuidelineDTO;
import vn.ducmai.core.web.command.AbstractCommand;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.util.List;

public class ListenGuidelineCommand extends AbstractCommand<ListenGuidelineDTO> {
    public ListenGuidelineCommand(){
        this.pojo=new ListenGuidelineDTO();
    }
    private String title;
    private String content;
private Integer exerciseListenId;
private Integer listenGuidelineId;
    public Integer getListenGuidelineId() {
        return listenGuidelineId;
    }

    public void setListenGuidelineId(Integer listenGuidelineId) {
        this.listenGuidelineId = listenGuidelineId;
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

    private List<Integer> ids;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
