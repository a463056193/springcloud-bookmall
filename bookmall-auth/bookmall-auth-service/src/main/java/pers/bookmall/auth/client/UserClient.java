package pers.bookmall.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import pers.bookmall.user.api.UserApi;

@FeignClient("user-service")
public interface UserClient extends UserApi {
}
