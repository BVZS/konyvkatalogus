package hu.soter.controller;

import hu.soter.modell.Book;

import java.util.ArrayList;

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

}
