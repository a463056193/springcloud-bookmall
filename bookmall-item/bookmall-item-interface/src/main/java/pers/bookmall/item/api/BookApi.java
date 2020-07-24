package pers.bookmall.item.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.bookmall.item.pojo.Book;
import pers.bookmall.item.pojo.Category;

import java.util.List;

public interface BookApi {

    @GetMapping("detail/{id}")
    //ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids") List<Long> ids);
    Book queryById(@PathVariable("id") Integer id);

}