package pers.bookmall.goodsweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.bookmall.goodsweb.client.BookClient;
import pers.bookmall.item.pojo.Book;

@Service
public class GoodsService {

    @Autowired
    private BookClient bookClient;

    public Book queryById(Integer id) {
        return this.bookClient.queryById(id);
    }
}
