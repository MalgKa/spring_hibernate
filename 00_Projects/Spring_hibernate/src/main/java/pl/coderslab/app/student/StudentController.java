package pl.coderslab.app.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/view/student")
public class StudentController {

    @GetMapping("/create")
    public String prepareCreateView(Model model){
        model.addAttribute("student", new Student());
        return "create-student";
    }
    @ModelAttribute("countries")
    public List<String> countries(){
        return List.of("Poland", "Germany", "France", "Russia", "Denmark");
    }

    @ModelAttribute("hobbies")
    public List<String> hobbies(){
        return List.of("sailing", "blogging", "travelling", "yoga");
    }
    @ModelAttribute("programmingSkills")
    public List<String> skills(){
        return List.of("java", "javaScript", "SQL", "Maven", "Git", "Linux");
    }
    @PostMapping("create")
    public String showStudent(Student student){
        log.info("Student has been saved: {}", student);
        return "student-saved";
    }


}
