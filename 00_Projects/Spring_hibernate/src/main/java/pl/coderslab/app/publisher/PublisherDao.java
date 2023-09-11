package pl.coderslab.app.publisher;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public void update(Publisher publisher) {
        entityManager.merge(publisher);
    }

    public void delete(Publisher publisher) {
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }

    public Publisher findById(Long id){
        return entityManager.find(Publisher.class, id);
    }

    public List<Publisher> findAll(){
        Query query = entityManager.createQuery("SELECT p FROM Publisher p ORDER BY id");
        return query.getResultList();
    }


}
