package pers.bookmall.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import pers.bookmall.common.pojo.PageResult;
import pers.bookmall.item.mapper.BookMapper;
import pers.bookmall.item.pojo.Book;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    /**
     * 查询所有书籍
     */
    public List<Book> findAllBook(){
        return bookMapper.selectAll();
    }

    /**
     * 分页查询所有书籍
     */
    public PageResult<Book> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {

        // 初始化example对象
        Example example = new Example(Book.class);
        Example.Criteria criteria = example.createCriteria();

        // 根据name模糊查询，或者根据首字母查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }

        // 添加分页条件
        PageHelper.startPage(page, rows);

        // 添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Book> brands = this.bookMapper.selectByExample(example);
        // 包装成pageInfo
        PageInfo<Book> pageInfo = new PageInfo<>(brands);
        // 包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public List<Book> queryBookByName(String name) {
        return this.bookMapper.queryBookByName(name);
    }

    public Book queryBookById(String bid) {
        return this.bookMapper.selectByPrimaryKey(bid);
    }
    private void sendMessage(Long id, String type){
        // 发送消息
        try {
            this.amqpTemplate.convertAndSend("item." + type, id);
        } catch (Exception e) {
            LOGGER.error("{}商品消息发送异常，商品id：{}", type, id, e);
        }
    }



    public Book queryById(Integer id) {
        return this.bookMapper.selectByPrimaryKey(id);
    }


    public List<Book> getAllBook(){
        return this.bookMapper.selectAll();
    }

    /**
     * 获取流行书籍
     * @return
     */

    public PageResult<Book> getBooksByType(String type, Integer page){

        Book record = new Book();
        record.setBtype(type);
        PageHelper.startPage(page, 6);
        List<Book> books = this.bookMapper.select(record);
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());

    }

}
