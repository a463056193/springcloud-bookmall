package pers.bookmall.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.bookmall.common.pojo.PageResult;
import pers.bookmall.item.pojo.*;
import pers.bookmall.item.service.BannerService;
import pers.bookmall.item.service.BookService;
import pers.bookmall.item.service.RecommendService;

import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private BookService bookService;

    @RequestMapping("multidata")
    public ResponseEntity<MultiData> createHome(){

        MultiData multiData = new MultiData();

        List<Banner> banners = this.bannerService.getBanners();
        List<Recommend> recommends = this.recommendService.getRecommends();
        multiData.setBanners(banners);
        multiData.setRecommends(recommends);

        return ResponseEntity.ok(multiData);
    }


    @RequestMapping("data")
    public ResponseEntity<Data> getData(@RequestParam String type, @RequestParam Integer page){
        Data data = new Data();
        PageResult<Book> booksByType = this.bookService.getBooksByType(type, page);
        List<Book> list = booksByType.getItems();
        data.setList(list);
        return ResponseEntity.ok(data);
    }

}
