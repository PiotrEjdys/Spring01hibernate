package pl.coderslab.converter;

import org.springframework.core.convert.converter.Converter;
import pl.coderslab.model.Author;
import pl.coderslab.dao.AuthorDao;

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

