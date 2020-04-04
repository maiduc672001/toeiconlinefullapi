package vn.ducmai.core.utils;

import vn.ducmai.core.dto.RoleDTO;
import vn.ducmai.core.dto.UserDTO;
import vn.ducmai.core.persistence.entity.RoleEntity;
import vn.ducmai.core.persistence.entity.UserEntity;

public class RoleBeanUtil {
        public static RoleDTO entityToDTO(RoleEntity roleEntity) {
            RoleDTO roleDTO=new RoleDTO();
            roleDTO.setRoleId(roleEntity.getRoleId());
            roleDTO.setName(roleEntity.getName());
            return roleDTO;
        }
        public static RoleEntity dTOTOEntity(RoleDTO roleDTO){
            RoleEntity roleEntity=new RoleEntity();
            roleEntity.setRoleId(roleDTO.getRoleId());
            roleEntity.setName(roleDTO.getName());
            return roleEntity;

        }
}
