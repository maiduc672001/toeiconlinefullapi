package vn.ducmai.core.service;

import vn.ducmai.core.dto.CheckLogin;
import vn.ducmai.core.dto.UserDTO;
import vn.ducmai.core.dto.UserImportDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    CheckLogin checklogin(String name,String password);
    Object[] findUserByProperties(Map<String, Object> mapProperties, String sortDirection, String sortExpression, Integer first, Integer limit);
    UserDTO findUserById(Integer id);
    void saveUser(UserDTO dto);
    void updateUser(UserDTO dto);
    Integer deleteUser(List<Integer> ids);
    List<UserImportDTO> validateUserImport(List<UserImportDTO> userImportDTOS);
    void saveUserImport(List<UserImportDTO> userImportDTOS);
    List<UserDTO> listUserDTO(List<String> names);
    UserDTO findUserUnique(String properties,String name);
}
