package pl.coderslab.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.app.book.Book;
import pl.coderslab.app.dao.BookDao;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookDao bookDao;

    public BookController(BookDao bookDao){
        this.bookDao=bookDao;
    }

    @GetMapping("add/{title}/{description}/{rating}")
    public String addBook(@PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("rating") int rating){
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setRating(rating);
        bookDao.saveBook(book);
        return "Id do podanej książki to:" + book.getId();
    }

    @GetMapping("get/{id}")
    public String getBook(@PathVariable long id){
        Book book = bookDao.findById(id);
        return book.toString();
    }
    @GetMapping("update/{id}/{title}/{description}/{rating}")
    public String updateBook(@PathVariable long id, @PathVariable String title, @PathVariable String description, @PathVariable int rating ){
        Book book = bookDao.findById(id);
        book.setTitle(title);
        book.setDescription(description);
        book.setRating(rating);
        bookDao.update(book);
        return book.toString();
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id){
        Book book =bookDao.findById(id);
        bookDao.delete(book);
        return book.toString();
    }


}
