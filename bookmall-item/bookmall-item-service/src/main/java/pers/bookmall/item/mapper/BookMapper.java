package pers.bookmall.item.mapper;

import org.apache.ibatis.annotations.Select;
import pers.bookmall.item.pojo.Book;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BookMapper extends Mapper<Book> {

    @Select("select * from book where name like concat('%',#{name},'%')")
    List<Book> queryBookByName(String name);
}
