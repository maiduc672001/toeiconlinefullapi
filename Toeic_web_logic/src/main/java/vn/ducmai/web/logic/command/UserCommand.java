package vn.ducmai.web.logic.command;

import vn.ducmai.core.dto.RoleDTO;
import vn.ducmai.core.dto.UserDTO;
import vn.ducmai.core.dto.UserImportDTO;
import vn.ducmai.core.web.command.AbstractCommand;

import java.util.List;

public class UserCommand extends AbstractCommand<UserDTO> {
    public UserCommand(){
        this.pojo=new UserDTO();
    }
    private List<RoleDTO> roles;
private String name;
private String fullName;
private String password;
private Integer userId;
private Integer roleId;
private List<Integer> ids;
private List<UserImportDTO> userImportDTOS;

    public List<UserImportDTO> getUserImportDTOS() {
        return userImportDTOS;
    }

    public void setUserImportDTOS(List<UserImportDTO> userImportDTOS) {
        this.userImportDTOS = userImportDTOS;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
