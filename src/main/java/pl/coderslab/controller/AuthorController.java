package pl.coderslab.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.BookDao;
import pl.coderslab.model.Author;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.model.Book;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;


@Controller
public class AuthorController {
    private final AuthorDao authorDao;
    private final BookDao bookDao;

    public AuthorController(AuthorDao authorDao,BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
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
    public String updateAuthor(@PathVariable long id
            ,@PathVariable String firstName, @PathVariable String lastName){
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.update(author);
        return author.toString();
    }
    @RequestMapping("/author/delete/{id}")
    @ResponseBody
    @Transactional
    public String deleteAuthor(@PathVariable long id){
        Author author = authorDao.findById(id);

        List<Book> books = author.getBooks();

        for (Book b:books) {
//            Hibernate.initialize(b.getAuthors());
            List<Author> authors = b.getAuthors();
            Iterator<Author> iterator = authors.iterator();
            while (iterator.hasNext()) {
                Author a = iterator.next();
                if (a.getId() == author.getId()) {
                    iterator.remove();
//                    authors.remove(a);
//                   b.setAuthors(authors);
                }
            }

        }
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
    public String getAuthorForm(@ModelAttribute("author") Author author){
        return "/authorform.jsp";
    }


    @PostMapping("/authorform")
    public String postAuthorForm(@Valid Author author, BindingResult result){
        if (result.hasErrors()){
            return "/authorform.jsp";
        }
        authorDao.saveAuthor(author);
        return "redirect:/author/all";
    }
    @RequestMapping("authorform/edit/{id}")
    public String editAuthorForm(@PathVariable long id,Model model){
        Author author = authorDao.findById(id);
        model.addAttribute("author",author);
        return "/authorform.jsp";
    }
    @PostMapping("authorform/edit/{id}")
    public String editPostAuthorForm(@PathVariable long id,@Valid Author author, BindingResult result){
        if (result.hasErrors()){
            return "/authorform.jsp";
        }
        authorDao.update(author);
        return "redirect:/author/all";
    }
    @RequestMapping("/author/choose/{id}")
    public String chooseAuthor(@PathVariable long id,Model model){
        model.addAttribute("id",id);
        return "/chooseauthor.jsp";
    }
}
