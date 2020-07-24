package pers.bookmall.order.service.api;

import org.springframework.cloud.openfeign.FeignClient;
import pers.bookmall.item.api.BookApi;

@FeignClient(value = "bookmall-gateway", path = "/api/item")
public interface BookService extends BookApi {
}

