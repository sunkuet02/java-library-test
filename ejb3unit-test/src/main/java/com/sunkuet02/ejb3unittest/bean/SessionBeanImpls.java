package com.sunkuet02.ejb3unittest.bean;

import com.sunkuet02.ejb3unittest.bean.ifaces.SessionBean;
import com.sunkuet02.ejb3unittest.entity.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by sun on 5/11/17.
 */

@Stateless
public class SessionBeanImpls implements SessionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public String getName() {
        return "EJB";
    }

    public void addBook(Book book ) {
        this.entityManager.persist(book);
    }

    public List<Book> getAll() {
        Query query = this.entityManager.createQuery("select b from Book b");
        return query.getResultList();
    }
}
