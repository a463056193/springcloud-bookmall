package pers.bookmall.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.bookmall.item.mapper.CategoryMapper;
import pers.bookmall.item.pojo.Category;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategory(){
        return this.categoryMapper.selectAll();
    }


}
