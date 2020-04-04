package vn.ducmai.core.service;

import vn.ducmai.core.dto.CheckLogin;

public interface UserService {
    CheckLogin checklogin(String name,String password);
}
