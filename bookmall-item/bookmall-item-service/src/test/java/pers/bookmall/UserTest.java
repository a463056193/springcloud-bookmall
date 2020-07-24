package pers.bookmall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.bookmall.item.pojo.Book;
import pers.bookmall.item.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {


    @Autowired
    private BookService bookService;

    @Autowired
    private HttpSession httpSession;

    @Test
    public void testLambda(){
        List<Book> books = bookService.findAllBook();
        /*books.forEach(book -> {
            System.out.println(book);
        });*/
        books.forEach(System.out::println);
    }



}
