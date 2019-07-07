package com.example.imoocnettymuxin.service.impl;

import com.example.imoocnettymuxin.mapper.UserMapper;
import com.example.imoocnettymuxin.org.n3r.idworker.Sid;
import com.example.imoocnettymuxin.pojo.User;
import com.example.imoocnettymuxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * Created by xianpeng.xia
 * on 2019-07-07 09:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        User user = new User();
        user.setUsername(username);
        User result = userMapper.selectOne(user);
        return result != null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserForLogin(String usename, String pwd) {
        Example userExample = new Example(User.class);
        Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username", usename);
        criteria.andEqualTo("password", pwd);
        User result = userMapper.selectOneByExample(userExample);
        return result;
    }

    @Override
    public User saveUser(User user) {
        String userId = sid.nextShort();
        //TODO
        user.setQrcode("");
        user.setId(userId);
        userMapper.insertSelective(user);
        return user;
    }
}
