package pl.coderslab;

import org.springframework.core.convert.converter.Converter;
import pl.coderslab.app.Author;
import pl.coderslab.app.AuthorDao;
import pl.coderslab.app.Publisher;
import pl.coderslab.app.PublisherDao;

public class AuthorConverter implements Converter<String,Author> {
    private AuthorDao authorDao;

    public AuthorConverter(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    @Override
    public Author convert(String s) {
        return authorDao.findById(Integer.parseInt(s));
    }
}

