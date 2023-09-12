package pl.coderslab.app.book;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.author.Author;
import pl.coderslab.app.author.AuthorDao;
import pl.coderslab.app.publisher.Publisher;
import pl.coderslab.app.publisher.PublisherDao;

import java.util.List;

@Controller
@RequestMapping("view/book")
public class BookViewController {
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final BookDao bookDao;

    public BookViewController(PublisherDao publisherDao, AuthorDao authorDao, BookDao bookDao) {
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }

    @GetMapping("list")
    public String getListView(Model model){
        model.addAttribute("books",  bookDao.findAll());
        return "books/list-view";
    }

    @GetMapping("add")
    public String getAddView(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("publisherList", publisherDao.findAll());
        model.addAttribute("authorList", authorDao.findAll());
        return "books/add-view";
    }

    @PostMapping("add")
    public String addBook(Book book){
        bookDao.save(book);
        return "redirect:/view/book/list"; //tu jest adres kontrolera a nie adres widoku !!!!!
    }

    @GetMapping("/update")
    public String getUpdateView(Model model, @RequestParam Long id){
        model.addAttribute("book", bookDao.findById(id));
        model.addAttribute("publisherList", publisherDao.findAll());
        model.addAttribute("authorList", authorDao.findAll());
        return "books/update-view";
    }

    @PostMapping("update")
    public String updateBook(Book book){
        bookDao.update(book);
        return "redirect:/view/book/list";
    }

    @GetMapping("delete")
    public String getDeleteView(Model model, @RequestParam Long id){
        model.addAttribute("book", bookDao.findById(id));
        return "books/delete-view";
    }
    @PostMapping("delete")
    public String deleteBook(@RequestParam Long id){
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "redirect:/view/book/list";
    }


}
