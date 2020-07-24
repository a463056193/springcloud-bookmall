package pers.bookmall.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import pers.bookmall.cart.interceptor.LoginInterceptor;
import pers.bookmall.cart.pojo.Cart;
import pers.bookmall.common.pojo.UserInfo;
import pers.bookmall.common.utils.JsonUtils;

@Service
public class CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:cart:";

    public void addCart(Cart cart) {
        // 获取用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        // 查询购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = this.redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getUid());

        String key = cart.getBookId().toString();
        Integer num = cart.getNum();

        // 判断当前的商品是否在购物车
        if(hashOperations.hasKey(key)){
            // 在，更新数量
            String cartJson = hashOperations.get(key).toString();
            cart = JsonUtils.parse(cartJson, Cart.class);
            cart.setNum(cart.getNum() + num);
            hashOperations.put(key, JsonUtils.serialize(cart));
        }else {
            // 不在，新增购物车
            cart.setUserId(userInfo.getUid());
            hashOperations.put(key, cart);
        }


    }
}
