package com.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import books.*;
import data.*;
import exception.custom.illegalAdminAccess;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class LibrarySystem extends Application {

    private List<Book> books;
    private static ArrayList<Student> userStudent = new ArrayList<>();
    private static ArrayList<Book> bookList = new ArrayList<>();

    private static Stage primaryStage;

    public LibrarySystem() {
        this.books = new ArrayList<>();
    }

    public static ArrayList<Student> getUserStudent() {
        return userStudent;
    }

    public static ArrayList<Book> getBookList() {
        return bookList;
    }

    public static void addBook(Book book) {
        bookList.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public static Student student = new Student(null, null, null, null);
    public static Admin admin = new Admin(null, null, null, null);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LibrarySystem.primaryStage = primaryStage;
        primaryStage.setTitle("Library System");

        Student userStudent = new Student("Farhan Fatihu Ihsan", "202310370311211", "Teknik", "Informatika");
        getUserStudent().add(userStudent);

        Book bookList = new Book("388c-e681-9152", "Pemograman Java OOP", "Anan", "Novel", 10, 5);
        getBookList().add(bookList);

        Book historyBook = new HistoryBook("14567", "G-30 S PKI", "Rifki", "Sejarah", 8, 8);
        addBook(historyBook);

        Book storyBook = new StoryBook("5678", "Pencari Surga", "Agus", "Cerita", 11, 9);
        addBook(storyBook);

        Book textBook = new TextBook("91011", "Si Kancil", "Andre", "Novel", 20, 10);
        addBook(textBook);


        mainMenu();
    }

    public void mainMenu() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        vbox.getStyleClass().add("vbox");

        Label title = new Label("====== Library System ======");
        title.getStyleClass().add("label-title");
        Button studentButton = new Button("Login Sebagai Mahasiswa");
        studentButton.getStyleClass().add("button");
        Button adminButton = new Button("Login Sebagai Admin");
        adminButton.getStyleClass().add("button");
        Button exitButton = new Button("Exit");
        exitButton.getStyleClass().add("button");

        studentButton.setOnAction(e -> loginStudent());
        adminButton.setOnAction(e -> loginAdmin());
        exitButton.setOnAction(e -> primaryStage.close());

        vbox.getChildren().addAll(title, studentButton, adminButton, exitButton);

        Scene scene = new Scene(vbox, 500, 500);
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loginStudent() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("vbox");

        Label label = new Label("Masukan NIM:");
        TextField nimField = new TextField();
        nimField.getStyleClass().add("text-field");
        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("button");

        loginButton.setOnAction(e -> {
            String NIM = nimField.getText();
            if (NIM.length() != 15) {
                showAlert(Alert.AlertType.ERROR, "NIM harus 15 digit angka.");
            } else if (checkNim(NIM)) {
                student.setPrimaryStage(primaryStage);
                student.menuStudent();
            } else {
                showAlert(Alert.AlertType.ERROR, "User Not Found!!");
            }
        });

        vbox.getChildren().addAll(label, nimField, loginButton);

        Scene scene = new Scene(vbox, 500, 500);
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }

    private boolean checkNim(String NIM) {
        for (Student student : userStudent) {
            if (student != null && student.getNIM().equals(NIM)) {
                return true;
            }
        }
        return false;
    }

    private void loginAdmin() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("vbox");

        Label usernameLabel = new Label("Masukan Username (admin):");
        TextField usernameField = new TextField();
        usernameField.getStyleClass().add("text-field");
        Label passwordLabel = new Label("Masukan Password (admin):");
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("text-field");
        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("button");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                if (admin.isAdmin(username, password)) {
                    admin.setPrimaryStage(primaryStage);
                    admin.menuAdmin();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Admin User Not Found!!");
                }
            } catch (illegalAdminAccess ex) {
                showAlert(Alert.AlertType.ERROR, "Illegal admin access: " + ex.getMessage());
            }
        });

        vbox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);

        Scene scene = new Scene(vbox, 500, 500); scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
