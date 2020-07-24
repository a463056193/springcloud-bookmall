package pers.bookmall.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.bookmall.item.pojo.DetailData;
import pers.bookmall.item.service.TopImageService;

@Controller

public class DetailController {

    @Autowired
    private TopImageService topImageService;

    @RequestMapping("detail")
    public ResponseEntity<DetailData> CreateDetailPage(@RequestParam("id")Integer id){

        DetailData detailData = new DetailData();
        detailData.setTopImages(this.topImageService.getTopImage(id));

        return ResponseEntity.ok(detailData);
    }

}
