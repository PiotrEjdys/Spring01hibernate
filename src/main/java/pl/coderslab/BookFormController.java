package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.Book;
import pl.coderslab.app.BookDao;
import pl.coderslab.app.Publisher;
import pl.coderslab.app.PublisherDao;

import java.util.List;

@Controller
public class BookFormController {
    private BookDao bookDao;
    private PublisherDao publisherDao;

    public BookFormController(BookDao bookDao, PublisherDao publisherDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
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


}
