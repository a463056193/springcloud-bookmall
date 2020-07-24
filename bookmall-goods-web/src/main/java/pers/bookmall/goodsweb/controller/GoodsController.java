package pers.bookmall.goodsweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pers.bookmall.goodsweb.client.BookClient;
import pers.bookmall.goodsweb.service.GoodsHtmlService;
import pers.bookmall.goodsweb.service.GoodsService;
import pers.bookmall.item.pojo.Book;

@Controller
@RequestMapping("item")
public class GoodsController {

    @Autowired
    private GoodsService goodsService ;

    @Autowired
    private GoodsHtmlService goodsHtmlService;


    /**
     * 跳转到商品详情页
     * @param model
     * @param id
     * @return
     */
    @GetMapping("{id}.html")
    public String toItemPage(Model model, @PathVariable("id")Integer id){

        Book book = this.goodsService.queryById(id);

        model.addAttribute(book);
        this.goodsHtmlService.asyncExcute(id);
        return "item";
    }

    @GetMapping("detail")
    @ResponseBody
    public String test(){
        return "yes";
    }


}