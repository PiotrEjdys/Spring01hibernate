package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.Person;
import pl.coderslab.app.PersonDao;
import pl.coderslab.app.PersonDetails;
import pl.coderslab.app.PersonDetailsDao;

@Controller
public class PersonController {
    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }

    @RequestMapping("/person/add")
    @ResponseBody
    public String addingNewPerson(){
        Person person = new Person();
        PersonDetails personDetails = new PersonDetails();
        person.setEmail("blabla@gmail.com");
        person.setLogin("blabla");
        person.setPassword("blablablapas");
        personDetails.setFirstName("BlaName");
        personDetails.setLastName("BlaSurname");
        personDetails.setCity("BlaCity");
        personDetails.setStreet("BlaStreet");
        personDetails.setStreetNumber("14A");
        person.setPersonDetails(personDetails);
        personDao.savePerson(person);
        return "Id of added author is: " + person.getId();
    }
    @RequestMapping("/person/get/{id}")
    @ResponseBody
    public String getPerson(@PathVariable long id){
        Person person = personDao.findById(id);
        return person.toString();
    }
    @RequestMapping("/person/update/{id}/{login}/{password}/{email}")
    @ResponseBody
    public String updatePerson(@PathVariable long id
            ,@PathVariable String password, @PathVariable String login,@PathVariable String email){
        Person person = personDao.findById(id);
       person.setLogin(login);
       person.setPassword(password);
       person.setEmail(email);
        personDao.update(person);
        return person.toString();
    }
    @RequestMapping("/person/delete/{id}")
    @ResponseBody
    public String deletePerson(@PathVariable long id){
        Person person = personDao.findById(id);
        personDao.delete(person);
        return "deleted";
    }
    @GetMapping("/person/form")
    public String getForm(@ModelAttribute("person")Person person){
        return "/form.jsp";
    }
//    @PostMapping("/person/form")
//    public  String postForm(Model model, String login, String email, String password ){
//        Person person = new Person();
//        person.setLogin(login);
//        person.setEmail(email);
//        person.setPassword(password);
//        model.addAttribute("person",person);
//        System.out.println(person);
//        return "/person.jsp";
//    }
    @PostMapping("/person/form")
    @ResponseBody
    public String addPerson( Person person) {
        return person.toString();
    }
}
