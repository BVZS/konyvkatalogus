package hu.soter.controller;

import hu.soter.modell.Book;

import java.io.*;
import java.util.*;

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

    public void saveToTextFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Book book : list) {
                writer.write(book.getTitle() + ";" + String.join(",", book.getAuthors()) + ";" +
                        book.getPublicationYear() + ";" + book.getPrice());
                writer.newLine();
            }
        }
    }

    public void loadFromTextFile(String filename) throws IOException {
        list.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String title = parts[0];
                Set<String> authors = new HashSet<>(Arrays.asList(parts[1].split(",")));
                int publicationYear = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);
                Book book = new Book(title, authors, publicationYear, price);
                list.add(book);
            }
        }
    }

    public void saveToBinaryFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
        }
    }

    public void loadFromBinaryFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            list = (ArrayList<Book>) in.readObject();
        }
    }




}
