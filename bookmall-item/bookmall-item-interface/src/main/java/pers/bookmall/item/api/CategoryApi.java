package pers.bookmall.item.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.bookmall.item.pojo.Category;

import java.util.List;

@RequestMapping("category")
public interface CategoryApi {

    @GetMapping("list")
    //ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids") List<Long> ids);
   List<Category> queryCategory();
}