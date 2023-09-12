package pl.coderslab.app.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class Student {
   private String firstName;
    private String lastName;
    private String gender;
    private String country;
    private String notes;
    boolean mailingList;
    private List<String> programmingSkills;
    private List<String> hobbies;


}
