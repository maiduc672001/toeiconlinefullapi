package vn.ducmai.core.utils;

import vn.ducmai.core.dto.UserDTO;
import vn.ducmai.core.persistence.entity.UserEntity;

public class UserBeanUtil {
    public static UserDTO entityToDTO(UserEntity entity){
        UserDTO dto=new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setFullName(entity.getFullName());
        dto.setRoleDTO(RoleBeanUtil.entityToDTO(entity.getRoleEntity()));
        return  dto;
    }
    public static UserEntity dTOTOEntity(UserDTO dto){
        UserEntity entity=new UserEntity();
        entity.setUserId(dto.getUserId());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setFullName(dto.getFullName());
        entity.setRoleEntity(RoleBeanUtil.dTOTOEntity(dto.getRoleDTO()));
        return  entity;
    }
}
