package com.example.imoocnettymuxin.service;

import com.example.imoocnettymuxin.pojo.User;

/**
 * Created by xianpeng.xia
 * on 2019-07-07 09:15
 */
public interface UserService {

    boolean queryUsernameIsExist(String username);

    User queryUserForLogin(String usename, String pwd);

    User saveUser(User user);
}
