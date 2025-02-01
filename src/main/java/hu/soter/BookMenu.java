package hu.soter;

import hu.soter.controller.Database;
import hu.soter.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class BookMenu {
    private static Database databaseController;
    private static Scanner scanner = new Scanner(System.in);

    public static void load() {
        login();
        menu();
    }

    private static void menu() {
        int choice = 0;

        while (choice != 8) {
            menuString();
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    //deleteBook();
                    break;
                case 3:
                    //listBooks();
                    break;
                case 4:
                    //searchBook();
                    break;
                case 5:
                    //saveToFile();
                    break;
                case 6:
                    //loadFromFile();
                    break;
                case 7:
                    //saveToDatabase();
                    break;
                case 8:
                    System.out.println("Kilépés...");
                    break;
                default:
                    System.out.println("Érvénytelen választás, próbáld újra.");
            }
        }
    }

    private static void menuString() {
        System.out.println("\nFőmenü:");
        System.out.println("1. Könyv hozzáadása");
        System.out.println("2. Könyv törlése");
        System.out.println("3. Könyvek listázása");
        System.out.println("4. Könyv keresése");
        System.out.println("5. Mentés fájlba");
        System.out.println("6. Betöltés fájlból");
        System.out.println("7. Mentés adatbázisba");
        System.out.println("8. Kilépés");
        System.out.print("Válaszd ki a műveletet: ");
    }

    private static void login() {
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
