package pl.coderslab;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.app.Publisher;
import pl.coderslab.app.PublisherDao;

public class PublisherConverter implements Converter<String, Publisher> {
    private PublisherDao publisherDao;

    public PublisherConverter(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Override
    public Publisher convert(String source) {
        return publisherDao.findById(Integer.parseInt(source));
    }
}
