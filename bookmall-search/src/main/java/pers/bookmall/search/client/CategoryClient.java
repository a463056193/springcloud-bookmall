package pers.bookmall.search.client;

import org.springframework.cloud.openfeign.FeignClient;
import pers.bookmall.item.api.CategoryApi;

@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}