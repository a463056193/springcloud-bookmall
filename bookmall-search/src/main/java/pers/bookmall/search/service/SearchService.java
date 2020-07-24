package pers.bookmall.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.bookmall.search.client.CategoryClient;
import pers.bookmall.search.pojo.Category;

import java.io.IOException;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private CategoryClient categoryClient;


}
