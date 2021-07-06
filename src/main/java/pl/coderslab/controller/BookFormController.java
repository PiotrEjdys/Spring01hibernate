package pl.coderslab.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

import java.util.List;

@Controller
public class BookFormController {
    private BookDao bookDao;
    private PublisherDao publisherDao;
    private AuthorDao authorDao;

    public BookFormController(BookDao bookDao, PublisherDao publisherDao,AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/bookform")
    public String getBookForm(@ModelAttribute("book") Book book){
        return "/bookform.jsp";
    }
    @PostMapping("/bookform")
    public String postBookForm(Book book){
        bookDao.saveBook(book);
        return "redirect:/book/all";
    }
    @ModelAttribute("publishers")
    public List<Publisher> show(){
        List<Publisher> publishers = publisherDao.showAllPub();
        return publishers;
    }
    @ModelAttribute("authors")
    public List<Author> showAut(){
        List<Author> authors = authorDao.showAllAut();
        return authors;
    }
    @RequestMapping("bookform/edit/{id}")
    public String editBookForm(@PathVariable long id,Model model){
        Book book =bookDao.findById(id);
        model.addAttribute("book",book);
        return "/bookformedit.jsp";
    }
    @PostMapping("bookform/edit/{id}")
    public String editPostBookForm(@PathVariable long id,Book book){
        bookDao.update(book);
        return "redirect:/book/all";
    }



}
