package vn.ducmai.core.service;

import vn.ducmai.core.dto.RoleDTO;
import java.util.List;

public interface RoleService  {
    List<RoleDTO> findAllRole();
    RoleDTO findRoleBy(Integer id);
    RoleDTO findRoleUnique(String property,String name);
}
