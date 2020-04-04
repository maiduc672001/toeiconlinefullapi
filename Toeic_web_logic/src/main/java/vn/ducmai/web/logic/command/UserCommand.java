package vn.ducmai.web.logic.command;

import vn.ducmai.core.dto.UserDTO;
import vn.ducmai.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO> {
    public UserCommand(){
        this.pojo=new UserDTO();
    }
}
