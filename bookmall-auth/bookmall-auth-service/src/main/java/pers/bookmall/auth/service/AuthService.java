package pers.bookmall.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.bookmall.auth.client.UserClient;
import pers.bookmall.auth.config.JwtProperties;
import pers.bookmall.common.pojo.UserInfo;
import pers.bookmall.common.utils.JwtUtils;
import pers.bookmall.user.pojo.User;

@Service
public class AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties jwtProperties;

    public String accredit(String username, String password) {
        // 1. 根据用户名密码查询
        User user = this.userClient.queryUser(username, password);
        // 2. 判断user
        if(user == null){
            return null;
        }

        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUid(user.getId());
            userInfo.setUsername(user.getUsername());
            return JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        // 3. jwtUtils生成token
    }
}
