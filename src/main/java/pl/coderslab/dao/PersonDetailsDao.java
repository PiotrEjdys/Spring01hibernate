package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.model.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDetailsDao {
    @PersistenceContext
    EntityManager entityManager;
    public void savePersonDetails(PersonDetails person){
        entityManager.persist(person);
    }
    public PersonDetails findById(long id){
        return entityManager.find(PersonDetails.class,id);
    }
    public void update(PersonDetails person){
        entityManager.merge(person);
    }
    public void delete(PersonDetails person){
        entityManager.
                remove(entityManager.contains(person)
                        ? person:entityManager.merge(person));

    }

}
