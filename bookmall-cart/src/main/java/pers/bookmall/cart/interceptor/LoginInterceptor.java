package pers.bookmall.cart.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pers.bookmall.cart.config.JwtProperties;
import pers.bookmall.common.pojo.UserInfo;
import pers.bookmall.common.utils.CookieUtils;
import pers.bookmall.common.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@EnableConfigurationProperties(JwtProperties.class)
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    @Autowired
    private JwtProperties jwtProperties;

    public LoginInterceptor(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取cookie中的token
        String token = CookieUtils.getCookieValue(request, this.jwtProperties.getCookieName());

        // 解析token,获取用户信息
        UserInfo userInfo= JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());

        if(userInfo == null){
            return false;
        }

        // 把userInfo放入线程局部变量
        THREAD_LOCAL.set(userInfo);
        return true;
    }

    // 获取UserInfo
    public static UserInfo getUserInfo(){
        return THREAD_LOCAL.get();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空线程的局部变量
        // 使用tomcat线程池， 线程不会结束，不会自动释放
        THREAD_LOCAL.remove();
    }
}
