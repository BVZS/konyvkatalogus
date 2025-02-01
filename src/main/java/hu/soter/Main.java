package hu.soter;

import hu.soter.controller.Database;
import hu.soter.model.User;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static Database databaseController;

    public static void main(String[] args) {
        databaseController = new Database();
        try {
            databaseController.connect();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Felhasználónév: ");
            String username = scanner.nextLine();

            System.out.print("Jelszó: ");
            String password = scanner.nextLine();

            User user = authenticate(username, password);

            if (user != null) {
                System.out.println("Sikeres bejelentkezés! Üdvözöljük, " + user.getUsername());
            } else {
                System.out.println("Hibás felhasználónév vagy jelszó!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static User authenticate(String username, String password) {
        try {
            return databaseController.authenticate(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
