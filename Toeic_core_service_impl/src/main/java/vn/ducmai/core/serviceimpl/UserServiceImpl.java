package vn.ducmai.core.serviceimpl;

import vn.ducmai.core.dto.CheckLogin;
import vn.ducmai.core.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public CheckLogin checklogin(String name, String password) {
CheckLogin checkLogin=new CheckLogin();
Object[] objects=SingletonDao.getUserDaoInstance().checkLogin(name,password);
checkLogin.setCheck((Boolean) objects[0]);
checkLogin.setRoleName((String) objects[1]);
return checkLogin;
    }
}
