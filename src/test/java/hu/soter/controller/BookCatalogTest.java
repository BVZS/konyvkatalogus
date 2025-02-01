package hu.soter.controller;

import hu.soter.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookCatalogTest {
    private BookCatalog catalog;

    @BeforeEach
    void setUp() {
        catalog = new BookCatalog();
    }

    @Test
    void testAddBook() {
        Set<String> authors = new HashSet<>();
        authors.add("Agatha Christie");
        Book book = new Book("Halál a Níluson", authors, 1937, 300);

        catalog.addBook(book);

        assertEquals(1, catalog.getList().size());
        assertTrue(catalog.getList().contains(book));
    }

    @Test
    void testRemoveBook() {
        Set<String> authors = new HashSet<>();
        authors.add("Agatha Christie");
        Book book = new Book("Halál a Níluson", authors, 1937, 300);

        catalog.addBook(book);
        catalog.removeBook(book.getId());

        assertEquals(0, catalog.getList().size());
    }

    @Test
    void testSearchBooksByTitle() {
        Set<String> authors = new HashSet<>();
        authors.add("Peter Gulutzan");
        authors.add("Trudy Pelzer");
        Book book = new Book("SQL Teljesítményfokozás", authors, 2003, 4655.0);

        catalog.addBook(book);

        catalog.searchBooks("SQL Teljesítményfokozás");
        assertTrue(catalog.getList().size() > 0);
    }

}