package pl.coderslab.app.person;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("persons")
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }


    @GetMapping("get")
    public String get(@RequestParam Long id){
        Person person= personDao.findById(id);
        return person.toString();
    }


    @PostMapping("create")
    public void create(@RequestParam String login, @RequestParam String password, @RequestParam String email){
        Person person = new Person();
        person.setLogin(login);
        person.setPassword(password);
        person.setEmail(email);
        personDao.save(person);
    }

    @PostMapping("update")
    public void update(@RequestParam Long id, @RequestParam String login, @RequestParam String password, @RequestParam String email){
        Person person = personDao.findById(id);
        person.setLogin(login);
        person.setPassword(password);
        person.setEmail(email);
        personDao.update(person);
    }

    @PostMapping("delete")
    public void delete(@RequestParam Long id){
        Person person = personDao.findById(id);
        personDao.delete(person);
    }






}

