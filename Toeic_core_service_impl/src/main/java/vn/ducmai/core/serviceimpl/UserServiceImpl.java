package vn.ducmai.core.serviceimpl;
import vn.ducmai.core.dto.CheckLogin;
import vn.ducmai.core.dto.UserDTO;
import vn.ducmai.core.dto.UserImportDTO;
import vn.ducmai.core.persistence.entity.RoleEntity;
import vn.ducmai.core.persistence.entity.UserEntity;
import vn.ducmai.core.service.UserService;
import vn.ducmai.core.utils.UserBeanUtil;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class UserServiceImpl implements UserService {
    @Override
    public CheckLogin checklogin(String name, String password) {
CheckLogin checkLogin=new CheckLogin();
Object[] objects=SingletonDao.getUserDaoInstance().checkLogin(name,password);
checkLogin.setCheck((Boolean) objects[0]);
checkLogin.setRoleName((String) objects[1]);
return checkLogin;
    }

    @Override
    public Object[] findUserByProperties(Map<String, Object> mapProperties, String sortDirection, String sortExpression, Integer first, Integer limit) {
        Object[] objects=SingletonDao.getUserDaoInstance().findByProperties(mapProperties,sortDirection,sortExpression,first,limit);
        List<UserDTO> dtos=new ArrayList<UserDTO>();
        List<UserEntity> entities= (List<UserEntity>) objects[1];
        for (UserEntity entity:entities) {
            UserDTO dto= UserBeanUtil.entityToDTO(entity);
            dtos.add(dto);
        }
        objects[1]=dtos;
        return objects;
    }

    @Override
    public UserDTO findUserById(Integer id) {
        UserEntity entity= SingletonDao.getUserDaoInstance().findById(id);
        return UserBeanUtil.entityToDTO(entity);
    }

    @Override
    public void saveUser(UserDTO dto) {
        UserEntity entity=UserBeanUtil.dTOTOEntity(dto);
        SingletonDao.getUserDaoInstance().save(entity);
    }

    @Override
    public void updateUser(UserDTO dto) {
        UserEntity entity=UserBeanUtil.dTOTOEntity(dto);
        SingletonDao.getUserDaoInstance().update(entity);
    }

    @Override
    public Integer deleteUser(List<Integer> ids) {
        return SingletonDao.getUserDaoInstance().delete(ids);
    }

    @Override
    public List<UserImportDTO> validateUserImport(List<UserImportDTO> userImportDTOS) {
List<String> userNames=new ArrayList<String>();
List<String> roleNames=new ArrayList<String>();
for (UserImportDTO dto:userImportDTOS){
    if(dto.isCheck()) {
        if (!userNames.contains(dto.getUserName())) {
            userNames.add(dto.getUserName());
        }
        if (!roleNames.contains(dto.getRoleName())) {
            roleNames.add(dto.getRoleName());
        }
    }
}
List<UserEntity> userEntities=SingletonDao.getUserDaoInstance().findAllByName(userNames);
List<RoleEntity> roleEntities=SingletonDao.getRoleDaoInstance().findAllByNames(roleNames);
Map<String,Object> mapUser=new HashMap<String, Object>();
        Map<String,Object> mapRole=new HashMap<String, Object>();
        for (UserEntity item:userEntities) {
            mapUser.put(item.getName(),item);
        }
        for (RoleEntity item:roleEntities) {
            mapRole.put(item.getName(),item);
        }
        for (UserImportDTO item:userImportDTOS) {
            if(item.isCheck()){
                if(mapUser.get(item.getUserName())!=null){
                    item.setCheck(false);
                }
                if(mapRole.get(item.getRoleName())==null){
                    item.setCheck(false);
                }
            }
        }
        return userImportDTOS;
    }

    @Override
    public void saveUserImport(List<UserImportDTO> userImportDTOS) {
         userImportDTOS=validateUserImport(userImportDTOS);
        for (UserImportDTO dto:userImportDTOS) {
            if(dto.isCheck()){
                UserEntity dto1=new UserEntity();
                dto1.setName(dto.getUserName());
                dto1.setFullName(dto.getFullName());
                dto1.setPassword(dto.getPassword());
                RoleEntity role=SingletonDao.getRoleDaoInstance().findEqualUnique("name",dto.getRoleName());
                dto1.setRoleEntity(role);
                Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                dto1.setCreatedDate(timestamp);
                SingletonDao.getUserDaoInstance().save(dto1);
            }
        }
    }


    @Override
    public List<UserDTO> listUserDTO( List<String> names) {

        return null;
    }

    @Override
    public UserDTO findUserUnique(String properties,String name) {
        UserEntity entity=SingletonDao.getUserDaoInstance().findEqualUnique(properties,name);
        return UserBeanUtil.entityToDTO(entity);
    }
}
