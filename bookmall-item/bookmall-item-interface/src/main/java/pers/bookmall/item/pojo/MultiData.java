package pers.bookmall.item.pojo;

import java.util.List;

public class MultiData {

    private List<Banner> banners;
    private List<Recommend> recommends;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Recommend> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<Recommend> recommends) {
        this.recommends = recommends;
    }
}
