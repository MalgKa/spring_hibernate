package pl.coderslab.app.book;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.author.Author;
import pl.coderslab.app.publisher.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public Book findById(Long id) {
        Book book = entityManager.find(Book.class, id);
//        Hibernate.initialize(book.getAuthors());
        return book;
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }


//    public List<Book> findAll() {
//        Query query = entityManager.createQuery("SELECT b FROM Book b");
//        return query.getResultList();
//    }

    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b FETCH ALL PROPERTIES", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> booksByRating(Integer rating) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.rating>:rating", Book.class)
                .setParameter("rating", rating)
                .getResultList();


//        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating = :rating", Book.class);
//        query.setParameter("rating", rating);
//        List<Book> books = query.getResultList();
//        return books;
    }

    public List<Book> findAllWithPublisher() {
        return entityManager.createQuery("SELECT b FROM Book b JOIN b.publisher", Book.class).getResultList();
    }

    public List<Book> findByPublisher(Publisher publisher) {
        return entityManager.createQuery("SELECT b from Book b WHERE b.publisher=:publisher", Book.class)
                .setParameter("publisher", publisher)
                .getResultList();


    }

    public List<Book> findByAuthor(Author author) {
        return entityManager.createQuery("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.authors WHERE  : author MEMBER OF b.authors", Book.class)
                .setParameter("author", author)
                .getResultList();
    }

}
