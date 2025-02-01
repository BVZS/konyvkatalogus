package hu.soter.model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        Set<String> authors = new HashSet<>();
        authors.add("J.R.R. Tolkien");
        book = new Book("A Gyűrűk Ura I.", authors, 1954, 500.0);
    }

    @Test
    void testConstructor() {
        assertEquals("A Gyűrűk Ura I.", book.getTitle());
        assertTrue(book.getAuthors().contains("J.R.R. Tolkien"));
        assertEquals(1954, book.getPublicationYear());
        assertEquals(500, book.getPrice());
    }

    @Test
    void testToString() {
        String expected = "Book{" +
                "authors=[J.R.R. Tolkien], " +
                "publicationYear=1954, " +
                "price=500, " +
                "id=" + book.getId() +
                ", title='A Gyűrűk Ura I.'}";

        assertTrue(book.toString().startsWith("Book{"));
    }
}