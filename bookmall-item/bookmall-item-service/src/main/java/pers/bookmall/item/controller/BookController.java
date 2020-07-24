package pers.bookmall.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import pers.bookmall.common.pojo.PageResult;
import pers.bookmall.item.pojo.Book;
import pers.bookmall.item.service.BookService;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Book>> queryBooksByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false)String sortBy,
            @RequestParam(value = "desc", required = false)Boolean desc
    ){
        PageResult<Book> result = this.bookService.queryBrandsByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("search")
    public ResponseEntity<List<Book>> queryBookByName(@RequestParam("name")String name){
        List<Book> books = this.bookService.queryBookByName(name);
        if(CollectionUtils.isEmpty(books)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }



    @PostMapping("save")
    public ResponseEntity saveBook(Book book) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @GetMapping("all")
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> books = this.bookService.getAllBook();
        if(CollectionUtils.isEmpty(books)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("detail/{id}")
    ResponseEntity<Book> queryById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(bookService.queryById(id));
    }

}
