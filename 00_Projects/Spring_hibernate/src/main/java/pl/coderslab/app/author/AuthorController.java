package pl.coderslab.app.author;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.book.BookDao;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorController {

    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    @GetMapping("get")
    public String get(@RequestParam Long id){
        Author author= authorDao.findById(id);
        return author.toString();
    }


    @PostMapping("create")
    public void create(@RequestParam String firstName, @RequestParam String lastName){
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.save(author);
    }

    @PostMapping("update")
    public void update(@RequestParam Long id, @RequestParam String firstName, @RequestParam String lastName){
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.update(author);
    }

    @PostMapping("delete")
    public void delete(@RequestParam Long id){
        Author author = authorDao.findById(id);
        authorDao.delete(author);
    }

    @GetMapping("all")
    public String allAuthors(){
        List<Author> authors = authorDao.findAll();
        return authors.toString();
    }







}
