package pl.coderslab.app.publisher;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("publishers")
public class PublisherController {

    private final PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @PostMapping("create")
    public void create(@RequestParam String name) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherDao.save(publisher);
    }

    @GetMapping("get")
    public String get(@RequestParam Long id) {
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @PostMapping("update")
    public void update(@RequestParam Long id, @RequestParam String name) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        publisherDao.update(publisher);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return publisher.toString();
    }

    @GetMapping("all")
    public String allPublishers() {
        List<Publisher> publishers = publisherDao.findAll();
        return publishers.toString();

    }

}
