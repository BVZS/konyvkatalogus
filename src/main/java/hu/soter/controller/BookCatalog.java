package hu.soter.controller;

import hu.soter.modell.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookCatalog {
    private ArrayList<Book> list;

    public BookCatalog() {
        this.list = new ArrayList<>();
    }

    public void addBook(Book book) {
        list.add(book);
    }

    public void removeBook(int id) {
        list.removeIf(book -> book.getId() == id);
    }

    public void listBooks() {
        for (Book book : list) {
            book.toString();
        }
    }

    public void searchBooks(String search) {
        List<Book> result = new ArrayList<>();

        for (Book book : list) {
            // cím alapján
            if (book.getTitle().toLowerCase().contains(search.toLowerCase())) {
                result.add(book);
            }
            // szerzők alapján
            else if (book.getAuthors().stream().anyMatch(author -> author.toLowerCase().contains(search.toLowerCase()))) {
                result.add(book);
            }
        }

        if (result.isEmpty()) {
            System.out.println("Nem található könyv a keresési értékre: " + search);
        } else {
            System.out.println("A keresési érték ("+ search +") alapján talált könyvek: ");
            for (Book book : result) {
                System.out.println(book);
            }
        }
    }

    public void sortByPrice() {
        list.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return Double.compare(b1.getPrice(), b2.getPrice());
            }
        });
    }

    public void sortByPublicationYear() {
        list.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return Integer.compare(b1.getPublicationYear(), b2.getPublicationYear());
            }
        });
    }




}
