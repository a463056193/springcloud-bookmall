package pers.bookmall.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.bookmall.item.pojo.Category;
import pers.bookmall.item.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategory(){
        List<Category> categories = this.categoryService.queryCategory();
        if(CollectionUtils.isEmpty(categories)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categories);
    }

}
