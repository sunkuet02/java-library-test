package com.sunkuet02.ejb3unittest.bean.ifaces;

import com.sunkuet02.ejb3unittest.entity.Book;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sun on 5/11/17.
 */

@Remote
public interface SessionBean {

    EntityManager getEntityManager();

    String getName();

    void addBook(Book book);

    List<Book> getAll();
}
