package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

import java.util.ArrayList;
import java.util.List;


@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;


    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @RequestMapping(value = "/book/add")
    @ResponseBody
    public String addBook() {
        Publisher publisher = new Publisher();
        publisher.setName("publisher");
        Author author1 = authorDao.findById(1);
        Author author2 = authorDao.findById(2);
        List<Author>authors =new ArrayList<>();
        authors.add(author1);
        authors.add(author2);
        publisherDao.savePublisher(publisher);
        Book book = new Book();
        book.setTitle("Thinking in Java ");
        book.setPublisher(publisher);
        book.setAuthors(authors);
        bookDao.saveBook(book);
        return "Id dodanej książki to:" + book.getId();
    }
    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id){
        Book book = bookDao.findById(id);
        return book.toString();
    }
    @RequestMapping("/book/update/{id}/{title}/{description}/{rating}")
    @ResponseBody
    public String updateBook(@PathVariable long id,
                             @PathVariable String title, @PathVariable String description
            ,@PathVariable int rating){
        Book book = bookDao.findById(id);
        book.setTitle(title);
        book.setDescription(description);
        book.setRating(rating);
        bookDao.update(book);
        return book.toString();
    }
    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id){
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";
    }
    @RequestMapping("/book/all")
    public String showAllBooks(Model model){
        List<Book>all = bookDao.showAllBooks();
        model.addAttribute("books",all);
        return "/books.jsp";
    }
    @RequestMapping("/bookbyrating/{rating}")
    public String showAllBooks(Model model,@PathVariable int rating){
        List<Book>all = bookDao.findAllByRating(rating);
        model.addAttribute("books",all);
        return "/books.jsp";
    }
    @RequestMapping("/bookpub")
    public String showAllBooksPub(Model model){
        List<Book>all = bookDao.findAllWithPublisher();
        model.addAttribute("books",all);
        return "/books.jsp";
    }
    @RequestMapping("/bookbypub")
    public String showAllBooksByPub(Model model) {
        Publisher publisher = publisherDao.findById(2);
        List<Book> all = bookDao.findAllByPublisherParam(publisher);
        model.addAttribute("books", all);
        return "/books.jsp";

    }
    @RequestMapping("/bookbyaut")
    public String showAllBooksByAut(Model model) {
        Author author = authorDao.findById(2);
        List<Book> all = bookDao.findAllByAuthorParam(author);
        model.addAttribute("books", all);
        return "/books.jsp";

    }
    @RequestMapping("/book/choose/{id}")
    public String choose(@PathVariable long id,Model model){
        model.addAttribute("id",id);
        return "/choose.jsp";
    }


}
