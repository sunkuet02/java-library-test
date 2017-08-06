package com.sunkuet02.ejb3unittest.entity;

import com.bm.testsuite.PoJoFixture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 5/14/17.
 */
public class POJOTest extends PoJoFixture{

    private static final Class<?>[] USED_ENTITIES={Book.class};

    public POJOTest() {
        super(USED_ENTITIES);
    }

    public void testToWriteComplexObjectGraph() {
        List<Book> complexObjectGraph = genrateTempBooks();

        // persist the graph and load it again
        List<Book> persisted = persist(complexObjectGraph);
        List<Book> allFromDB = findAll(Book.class);

        for(Book book : persisted) {
            System.out.println(book.getId());
        }

        // assert the persisted graph and the loaded are equal
        assertCollectionsEqual(persisted, allFromDB);
    }

    public void testGetEntityManager() {
        assertNotNull(this.getEntityManager());
    }
    private List<Book> genrateTempBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("1"));
        books.add(new Book("2"));
        books.add(new Book("3"));
        books.add(new Book("4"));

        return books;
    }

}
