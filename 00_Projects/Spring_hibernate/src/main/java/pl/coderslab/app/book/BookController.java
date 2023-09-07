package pl.coderslab.app.book;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.author.Author;
import pl.coderslab.app.author.AuthorDao;
import pl.coderslab.app.publisher.Publisher;
import pl.coderslab.app.publisher.PublisherDao;

import java.util.List;


@RestController
@RequestMapping("books")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }


    @PostMapping("create")
    public void create(@RequestParam String title, @RequestParam String description,
                       @RequestParam Integer rating, @RequestParam Long publisherId, @RequestParam List<Long> authorIds) {
        Book book = new Book();
        for (Long authorId : authorIds) {
            Author author = authorDao.findById(authorId);
            book.getAuthors().add(author);
        }
        Publisher publisher = publisherDao.findById(publisherId);
        book.setTitle(title);
        book.setDescription(description);
        book.setRating(rating);
        book.setPublisher(publisher);
        bookDao.save(book);
    }

    @GetMapping("get")
    public String getBook(@RequestParam Long id) {
        return bookDao.findById(id).toString();
    }

    @PostMapping("update")
    public void update(@RequestParam Long id, @RequestParam String title,
                       @RequestParam String description, @RequestParam Integer rating, @RequestParam Long publisherId, @RequestParam List<Long> authorIds) {
        Book book = bookDao.findById(id);
        Publisher publisher = publisherDao.findById(publisherId);
        for (Long authorId : authorIds) {
            Author author = authorDao.findById(authorId);
            book.getAuthors().add(author);
        }
        book.setTitle(title);
        book.setDescription(description);
        book.setRating(rating);
        book.setPublisher(publisher);
        bookDao.update(book);
    }

    @PostMapping("delete")
    public String delete(@RequestParam Long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return book.toString();
    }


}
