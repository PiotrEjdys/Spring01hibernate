package pl.coderslab.converter;


import org.springframework.core.convert.converter.Converter;
import pl.coderslab.model.Publisher;
import pl.coderslab.dao.PublisherDao;

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
