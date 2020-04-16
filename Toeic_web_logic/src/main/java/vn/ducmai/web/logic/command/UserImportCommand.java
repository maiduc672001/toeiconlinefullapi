package vn.ducmai.web.logic.command;

import vn.ducmai.core.dto.UserImportDTO;
import vn.ducmai.core.web.command.AbstractCommand;

public class UserImportCommand extends AbstractCommand<UserImportDTO> {
    public UserImportCommand(){
        this.pojo=new UserImportDTO();
    }
}
