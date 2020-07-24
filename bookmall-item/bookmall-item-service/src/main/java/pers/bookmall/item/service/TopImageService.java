package pers.bookmall.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.bookmall.item.mapper.TopImageMapper;
import pers.bookmall.item.pojo.TopImage;

import java.util.List;

@Service
public class TopImageService {

    @Autowired
    private TopImageMapper topImageMapper;

    /**
     * 获取图片
     */
    public List<TopImage> getTopImage(Integer id){
        TopImage record = new TopImage();
        record.setId(id);
        return this.topImageMapper.select(record);
    }

}
