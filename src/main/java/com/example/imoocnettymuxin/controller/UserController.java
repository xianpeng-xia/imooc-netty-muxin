package com.example.imoocnettymuxin.controller;

import com.example.imoocnettymuxin.org.n3r.idworker.Sid;
import com.example.imoocnettymuxin.pojo.User;
import com.example.imoocnettymuxin.pojo.vo.UserVO;
import com.example.imoocnettymuxin.service.UserService;
import com.example.imoocnettymuxin.utils.IMoocJSONResult;
import com.example.imoocnettymuxin.utils.MD5Utils;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xianpeng.xia
 * on 2019-07-06 13:16
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Sid sid;

    @PostMapping("/registOrLogin")
    public IMoocJSONResult registOrLogin(@RequestBody User user) throws NoSuchAlgorithmException {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return IMoocJSONResult.errorMsg("username or password can't be null");
        }
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        User userResult = null;
        //存在则登录，否则注册
        if (usernameIsExist) {
            userResult = userService.queryUserForLogin(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));
            if (userResult == null) {
                return IMoocJSONResult.errorMsg("username or password is illegal");
            }
        } else {
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            userResult = userService.saveUser(user);
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userResult, userVO);
        return IMoocJSONResult.ok(userVO);
    }
}
