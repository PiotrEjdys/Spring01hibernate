package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;


import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
public class ValidationController {
    @Autowired
    private Validator validator;
    @GetMapping("/validate")
//    @ResponseBody
    public String validate(Model model){
        Book book = new Book();
        Set<ConstraintViolation<Book>> validate = validator.validate(book);
        for (ConstraintViolation<Book> violation:validate) {
            System.out.println(violation.getPropertyPath()+ " " + violation.getMessage());
        }
//        if (validate.isEmpty()){
//            return "ok";
//        }else {
//            return "error";
//        }
        model.addAttribute("validate",validate);
        return "/validate.jsp";

    }
}
