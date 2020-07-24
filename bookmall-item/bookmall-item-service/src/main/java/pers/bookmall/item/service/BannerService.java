package pers.bookmall.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.bookmall.item.mapper.BannerMapper;
import pers.bookmall.item.pojo.Banner;

import java.util.List;

@Service
public class BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    public List<Banner> getBanners(){
        return this.bannerMapper.selectAll();
    }

}
