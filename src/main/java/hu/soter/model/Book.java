package hu.soter.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Book extends Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<String> authors;
    private int publicationYear;
    private double price;

    public Book() {
        super("Ismeretlen Cím");
        this.authors = new HashSet<>();
        this.publicationYear = 0;
        this.price = 0.0;
    }

    /**
     * Könyv létrehozása.
     *
     * @param title: a köny címe
     * @param authors: a könyv szerzője (lehet több is)
     * @param publicationYear: a könyv megjelenésének éve
     * @param price: a könyv ára forintban
     */
    public Book(String title, Set<String> authors, int publicationYear, double price) {
        super(title);
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.price = price;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Könyvhöz való hasonlítás.
     *
     * @param other: egy másik Book objektum
     */
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "authors=" + authors +
                ", publicationYear=" + publicationYear +
                ", price=" + price +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
