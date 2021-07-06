package pl.coderslab.app;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;
    public void saveBook(Book book){
        entityManager.persist(book);
    }
    public Book findById(long id){
        return entityManager.find(Book.class,id);
    }
    public void update(Book book){
        entityManager.merge(book);
    }
    public void delete(Book book){
        entityManager.
                remove(entityManager.contains(book)
                        ? book:entityManager.merge(book));

    }
    public List<Book> showAllBooks(){
      return   entityManager.createQuery("SELECT b FROM Book b").getResultList();
    }
    public List<Book> findAllByRating(int rating) {
        Query query = (Query) entityManager.createQuery("SELECT book FROM Book book WHERE book.rating = :rating");
        query.setParameter("rating", rating);
        return query.getResultList();
    }
    public List<Book> findAllWithPublisher(){
        Query query = (Query) entityManager.createQuery("SELECT b FROM Book b JOIN b.publisher");
        return query.getResultList();
    }
    public List<Book> findAllByPublisherParam(Publisher publisher) {
        Query query = (Query) entityManager.createQuery("SELECT b FROM Book b JOIN b.publisher WHERE b.publisher.id =:publisher");
        query.setParameter("publisher", publisher.getId());
        List<Book> bookPublisher = query.getResultList();
        return bookPublisher;
    }
    public List<Book> findAllByAuthorParam(Author author) {
        Query query = (Query) entityManager.createQuery("SELECT DISTINCT b FROM Book b JOIN b.authors WHERE :author MEMBER OF b.authors");
        query.setParameter("author", author);
        List<Book> bookAuthor = query.getResultList();
        return bookAuthor;
    }

}
