package hu.soter.controller;

import hu.soter.model.Book;
import hu.soter.model.User;

import java.sql.*;
import java.util.*;

public class Database {
    private Connection connection;

    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/konyvkatalogus";
        String user = "root";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);
    }

    public List<Book> loadBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String authors = rs.getString("authors");
                int publicationYear = rs.getInt("publication_year");
                double price = rs.getDouble("price");

                Set<String> authorsSet = new HashSet<>(Arrays.asList(authors.split(",")));

                Book book = new Book(title, authorsSet, publicationYear, price);
                book.setId(id);
                books.add(book);
            }
        }
        return books;
    }

    public void saveBook(Book book) throws SQLException {
        String query = "INSERT INTO books (title, authors, publication_year, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, String.join(",", book.getAuthors()));
            stmt.setInt(3, book.getPublicationYear());
            stmt.setDouble(4, book.getPrice());
            stmt.executeUpdate();
        }
    }

    public void deleteBook(int bookId) throws SQLException {
        String query = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
        }
    }

    public void deleteBookWithTransaction(int bookId) throws SQLException {
        connection.setAutoCommit(false);
        try {
            deleteBook(bookId);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<User> loadUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                User user = new User(id, username, password, role);
                users.add(user);
            }
        }
        return users;
    }

}
