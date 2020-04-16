package vn.ducmai.core.serviceimpl;

import vn.ducmai.core.dto.RoleDTO;
import vn.ducmai.core.persistence.entity.RoleEntity;
import vn.ducmai.core.service.RoleService;
import vn.ducmai.core.utils.RoleBeanUtil;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Override
    public List<RoleDTO> findAllRole() {
        List<RoleEntity> roleEntities=SingletonDao.getRoleDaoInstance().findAll();
        List<RoleDTO> roles=new ArrayList<RoleDTO>();
        for (RoleEntity item:roleEntities) {
            RoleDTO dto= RoleBeanUtil.entityToDTO(item);
            roles.add(dto);
        }
        return roles;
    }

    @Override
    public RoleDTO findRoleBy(Integer id) {
        RoleEntity entity=SingletonDao.getRoleDaoInstance().findById(id);
        return RoleBeanUtil.entityToDTO(entity);
    }

    @Override
    public RoleDTO findRoleUnique(String property, String name) {
        RoleEntity entity=SingletonDao.getRoleDaoInstance().findEqualUnique(property,name);
        return RoleBeanUtil.entityToDTO(entity);

    }
}
