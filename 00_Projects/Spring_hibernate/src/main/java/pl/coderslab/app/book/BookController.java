package pl.coderslab.app.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.author.Author;
import pl.coderslab.app.author.AuthorDao;
import pl.coderslab.app.publisher.Publisher;
import pl.coderslab.app.publisher.PublisherDao;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @GetMapping("all")
    public String findAll() {
        List<Book> allBooks = bookDao.findAll();
        allBooks.forEach(b -> log.info(b.toString()));
        return allBooks.toString();
    }

    @GetMapping("byRating")
    public String getBooksById(@RequestParam Integer rating) {
        List<Book> booksWithRating = bookDao.booksByRating(rating);
        booksWithRating.forEach(b -> b.toString());
        return booksWithRating.toString();
    }

    @GetMapping("withPublisher")
    public String findAllWithPublisher() {
        return bookDao.findAllWithPublisher().stream()
                .map(b -> String.format("%d. '%s',description:%s, rating:%d, publisher:%s", b.getId(), b.getTitle(), b.getDescription(), b.getRating(), b.getPublisher()))
                .collect(Collectors.joining("\n"));
    }

    @GetMapping("byPublisher")
    public String findByPublisher(@RequestParam Long publisherId) {
        Publisher publisher = publisherDao.findById(publisherId);
        return bookDao.findByPublisher(publisher).stream()
                .map(b -> String.format("%d., %s, description: %s, rating: %s, publisher: %s", b.getId(), b.getTitle(), b.getDescription(), b.getRating(), b.getPublisher()))
                .collect(Collectors.joining("\n"));

    }
    @GetMapping("byAuthor")
    public String findByAuthor(@RequestParam Long authorId){
        Author author = authorDao.findById(authorId);
       return bookDao.findByAuthor(author).stream()
               .map(b -> String.format("%d., %s, description: %s, rating: %s, publisher: %s author: %s", b.getId(), b.getTitle(), b.getDescription(), b.getRating(), b.getPublisher(), b.getAuthors()))
               .collect(Collectors.joining("\n"));


    }
}
