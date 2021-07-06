package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.Book;
import pl.coderslab.app.Publisher;
import pl.coderslab.app.PublisherDao;

import java.util.List;

@Controller
public class PublisherController {
    private final PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }
    @RequestMapping( value = "/publisher/add")
    @ResponseBody
    public String addingNewBook(){
        Publisher publisher = new Publisher();

        publisher.setName("Publisher1");
        publisherDao.savePublisher(publisher);
        return "Id of added publisher is: " + publisher.getId();
    }
    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id){
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }
    @RequestMapping("/publisher/update/{id}/{name}")
    @ResponseBody
    public String updateBook(@PathVariable long id,
                             @PathVariable String name){
        Publisher publisher = publisherDao.findById(id);
       publisher.setName(name);
        publisherDao.update(publisher);
        return publisher.toString();
    }
    @RequestMapping("/publisher/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id){
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return "deleted";
    }
    @RequestMapping("/publisher/all")
    public String showAllPub(Model model){
        List<Publisher> all = publisherDao.showAllPub();
        model.addAttribute("publishers",all);
        return "/publisher.jsp";
    }


}
