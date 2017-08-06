package com.sunkuet02.ejb3unittest.bean;

import com.bm.testsuite.BaseSessionBeanFixture;
import com.bm.testsuite.dataloader.EntityInitialDataSet;
import com.sunkuet02.ejb3unittest.entity.Book;

import javax.persistence.Query;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by sun on 5/11/17.
 */
public class SessionBeanImplsTest extends BaseSessionBeanFixture<SessionBeanImpls>{
    private static final Class<?>[] usedBeans = {Book.class};

    public SessionBeanImplsTest() {
        super(SessionBeanImpls.class, usedBeans, new BookStockInitialDataset());
    }

    public void testDependencyInjection() {
        final SessionBeanImpls toTest = this.getBeanToTest();
        assertNotNull(toTest);

        assertNotNull(toTest.getEntityManager());
        assertEquals(toTest.getName(), "EJB");
    }

    public void testAddBook() {

        final SessionBeanImpls toTest = this.getBeanToTest();

        assertNotNull(toTest);
        assertNotNull(toTest.getEntityManager());

        toTest.addBook(new Book("khaise"));

        List<Book> books = toTest.getAll();

        assertNotNull(books);

        assertEquals(3,books.size());
    }

    public static class BookStockInitialDataset extends
            EntityInitialDataSet<Book> {

        public BookStockInitialDataset() {
            super(Book.class);
        }

        public void create() {
            this.add(new Book("sun"));
            this.add(new Book("tamim"));
            this.add(new Book("datta"));
        }
    }

}
