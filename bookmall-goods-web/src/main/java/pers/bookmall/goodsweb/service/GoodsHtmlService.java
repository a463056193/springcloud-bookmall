package pers.bookmall.goodsweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pers.bookmall.goodsweb.utils.ThreadUtils;
import pers.bookmall.item.pojo.Book;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoodsHtmlService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private TemplateEngine templateEngine;

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsHtmlService.class);

    /**
     * 创建html页面
     *
     * @param id
     * @throws Exception
     */
    public void createHtml(Integer id) {

        Map<String, Object> modelMap = new HashMap<>();

        PrintWriter writer = null;
        try {
            // 获取页面数据
            Book book = this.goodsService.queryById(id);

            // 创建thymeleaf上下文对象
            Context context = new Context();
            // 把数据放入上下文对象
            modelMap.put("name", book.getBname());
            modelMap.put("price", book.getPrice());
            context.setVariables(modelMap);

            // 创建输出流
            File file = new File("D:\\Nginx\\nginx-1.14.0\\html\\item\\" + id + ".html");
            writer = new PrintWriter(file);

            // 执行页面静态化方法
            templateEngine.process("item", context, writer);
        } catch (Exception e) {
            LOGGER.error("页面静态化出错：{}，"+ e, id);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 新建线程处理页面静态化
     * @param spuId
     */
    public void asyncExcute(Integer spuId) {
        ThreadUtils.execute(()->createHtml(spuId));
        /*ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                createHtml(spuId);
            }
        });*/
    }

    public void deleteHtml(Long id) {
        File file = new File("D:\\Nginx\\nginx-1.14.0\\html\\item\\", id + ".html");
        file.deleteOnExit();
    }
}