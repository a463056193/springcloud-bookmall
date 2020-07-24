package pers.bookmall.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.bookmall.item.mapper.RecommendMapper;
import pers.bookmall.item.pojo.Recommend;

import java.util.List;

@Service
public class RecommendService {

    @Autowired
    private RecommendMapper recommendMapper;

    public List<Recommend> getRecommends(){
        return this.recommendMapper.selectAll();
    }

}
