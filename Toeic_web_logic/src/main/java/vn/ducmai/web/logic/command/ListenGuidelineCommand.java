package vn.ducmai.web.logic.command;

import vn.ducmai.core.dto.ListenGuidelineDTO;
import vn.ducmai.core.web.command.AbstractCommand;

public class ListenGuidelineCommand extends AbstractCommand<ListenGuidelineDTO> {
    public ListenGuidelineCommand(){
        this.pojo=new ListenGuidelineDTO();
    }
}
