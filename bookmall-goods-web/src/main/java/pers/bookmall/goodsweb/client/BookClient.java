package pers.bookmall.goodsweb.client;

import org.springframework.cloud.openfeign.FeignClient;
import pers.bookmall.item.api.BookApi;

@FeignClient("item-service")
public interface BookClient extends BookApi {

}
