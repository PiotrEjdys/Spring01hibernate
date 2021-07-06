package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {
    @GetMapping("/student/form")
    public String getForm(@ModelAttribute("student")Student student){
        return "/studentform.jsp";
    }
    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }
    @ModelAttribute("skills")
    public List<String> skills() {
        List<String>programmingSkills = new ArrayList<String>();
        programmingSkills.add( "Java");
        programmingSkills.add("PHP");
        programmingSkills.add("Ruby");
        return programmingSkills;
    }
    @ModelAttribute("hobbies")
    public List<String> hobbies() {
        List<String>hobbies = new ArrayList<String>();
        hobbies.add( "hob1");
        hobbies.add("hob2");
        hobbies.add("hob3");
        return hobbies;
    }
    @PostMapping("/student/form")
    @ResponseBody
    public String show( Student student){
        System.out.println(student);
        return student.toString();
    }

}
