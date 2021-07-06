package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.Author;
import pl.coderslab.app.AuthorDao;
import pl.coderslab.app.Book;

import java.util.List;


@Controller
public class AuthorController {
    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }
    @RequestMapping("/author/add")
    @ResponseBody
    public String addingNewAuthor(){
        Author author = new Author();
        author.setFirstName("Author1");
        author.setLastName("Author1Sur");
        authorDao.saveAuthor(author);
        return "Id of added author is: " + author.getId();
    }
    @RequestMapping("/author/get/{id}")
    @ResponseBody
    public String getAuthor(@PathVariable long id){
        Author author = authorDao.findById(id);
        return author.toString();
    }
    @RequestMapping("/author/update/{id}/{firstName}/{lastName}")
    @ResponseBody
    public String updateBook(@PathVariable long id
            ,@PathVariable String firstName, @PathVariable String lastName){
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.update(author);
        return author.toString();
    }
    @RequestMapping("/author/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id){
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "deleted";
    }
    @RequestMapping("/author/all")
    public String showAllAuthors(Model model){
        List<Author> authors = authorDao.showAllAut();
        model.addAttribute("authors",authors);
        return "/author.jsp";
    }
    @GetMapping("/authorform")
    public String getBookForm(@ModelAttribute("author") Author author){
        return "/authorform.jsp";
    }


    @PostMapping("/authorform")
    public String postBookForm(Author author){
        authorDao.saveAuthor(author);
        return "redirect:/author/all";
    }
    @RequestMapping("authorform/edit/{id}")
    public String editAuthorForm(@PathVariable long id,Model model){
        Author author = authorDao.findById(id);
        model.addAttribute("author",author);
        return "/authoreditform.jsp";
    }
    @PostMapping("authorform/edit/{id}")
    public String editPostAuthorForm(@PathVariable long id,Author author){
        authorDao.update(author);
        return "redirect:/author/all";
    }
    @RequestMapping("/author/choose/{id}")
    public String chooseAuthor(@PathVariable long id,Model model){
        model.addAttribute("id",id);
        return "/chooseauthor.jsp";
    }
}
