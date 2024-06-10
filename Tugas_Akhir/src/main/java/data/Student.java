package data;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import books.*;
import exception.custom.util.iMenu;
import com.main.LibrarySystem;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Student extends User implements iMenu {

    private Stage primaryStage;
    private List<Book> borrowedBooks = new ArrayList<>();
    private LibrarySystem main = new LibrarySystem();

    public Student(String nama, String NIM, String fakultas, String prodi) {
        super(nama, NIM, fakultas, prodi);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public LibrarySystem librarySystem = new LibrarySystem();

    public void menuStudent() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        vbox.getStyleClass().add("vbox");

        Label title = new Label("==== Menu Mahasiswa ====");
        title.getStyleClass().add("label-title");
        Button showBorrowedButton = new Button("Tampilkan Buku yang Dipinjam");
        showBorrowedButton.getStyleClass().add("button");
        Button borrowButton = new Button("Pinjam Buku");
        borrowButton.getStyleClass().add("button");
        Button returnButton = new Button("Kembalikan Buku");
        returnButton.getStyleClass().add("button");
        Button displayBooksButton = new Button("Daftar Buku Tersedia");
        displayBooksButton.getStyleClass().add("button");
        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("button");

        showBorrowedButton.setOnAction(e -> showBorrowedBooks());
        borrowButton.setOnAction(e -> choiceBook());
        returnButton.setOnAction(e -> returnBooks());
        displayBooksButton.setOnAction(e -> displayBooks());
        logoutButton.setOnAction(e -> {
            LibrarySystem librarySystem = new LibrarySystem();
            librarySystem.start(primaryStage);
        });

        vbox.getChildren().addAll(title, showBorrowedButton, borrowButton, returnButton, displayBooksButton, logoutButton);

        Scene scene = new Scene(vbox, 500, 500);
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showBorrowedBooks() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("vbox");

        Label title = new Label("===== Daftar Buku Dipinjam =====");
        title.getStyleClass().add("label-title");
        vbox.getChildren().add(title);

        if (borrowedBooks.isEmpty()) {
            Label noBooksLabel = new Label("Tidak ada buku yang dipinjam.");
            vbox.getChildren().add(noBooksLabel);
        } else {
            for (Book book : borrowedBooks) {
                Label bookInfo = new Label(
                        "ID Buku: " + book.getId_buku() + "\n" +
                                "Judul Buku: " + book.getJudul() + "\n" +
                                "Penulis: " + book.getAuthor() + "\n" +
                                "Kategori: " + book.getCategory() + "\n" +
                                "Durasi: " + book.getDuration() + " hari\n" +
                                "-----------------------------"
                );
                vbox.getChildren().add(bookInfo);
            }
        }

        Button backButton = new Button("Kembali ke Menu Mahasiswa");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> menuStudent());

        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 500, 500);
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }

    public void choiceBook() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("vbox");

        Label title = new Label("===== Pilih Buku =====");
        title.getStyleClass().add("label-title");
        vbox.getChildren().add(title);

        ArrayList<Book> bookList = LibrarySystem.getBookList();
        for (Book buku : bookList) {
            if (buku != null) {
                Label bookInfo = new Label(
                        "ID Buku: " + buku.getId_buku() + "\n" +
                                "Judul Buku: " + buku.getJudul() + "\n" +
                                "Penulis: " + buku.getAuthor() + "\n" +
                                "Kategori: " + buku.getCategory() + "\n" +
                                "Stok Buku: " + buku.getStockBuku() + "\n" +
                                "-----------------------------"
                );
                vbox.getChildren().add(bookInfo);
            }
        }

        Label inputLabel = new Label("Masukkan ID Buku yang ingin dipinjam:");
        TextField idField = new TextField();
        idField.getStyleClass().add("text-field");
        Label durationLabel = new Label("Masukkan durasi peminjaman (maksimal 14 hari):");
        TextField durationField = new TextField();
        durationField.getStyleClass().add("text-field");
        Button borrowButton = new Button("Pinjam");
        borrowButton.getStyleClass().add("button");

        borrowButton.setOnAction(e -> {
            String bookId = idField.getText();
            int duration = Integer.parseInt(durationField.getText());

            Book bookToBorrow = null;
            for (Book buku : bookList) {
                if (buku.getId_buku().equals(bookId)) {
                    bookToBorrow = buku;
                    break;
                }
            }

            if (bookToBorrow == null) {
                showAlert(Alert.AlertType.ERROR, "Buku tidak ditemukan.");
            } else if (bookToBorrow.getStockBuku() <= 0) {
                showAlert(Alert.AlertType.ERROR, "Stok buku habis!");
            } else if (duration > 14) {
                showAlert(Alert.AlertType.ERROR, "Anda hanya dapat meminjam buku maksimal selama 14 hari.");
            } else {
                bookToBorrow.setStockBuku(bookToBorrow.getStockBuku() - 1);
                bookToBorrow.setDuration(duration);
                borrowedBooks.add(bookToBorrow);
                showAlert(Alert.AlertType.INFORMATION, "Peminjaman buku berhasil dilakukan.");
            }
        });

        vbox.getChildren().addAll(inputLabel, idField, durationLabel, durationField, borrowButton);

        Button backButton = new Button("Kembali ke Menu Mahasiswa");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> menuStudent());

        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 500, 500);
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }

    public void returnBooks() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("vbox");

        Label title = new Label("===== Buku yang Dipinjam =====");
        title.getStyleClass().add("label-title");
        vbox.getChildren().add(title);

        if (borrowedBooks.isEmpty()) {
            Label noBooksLabel = new Label("Anda belum meminjam buku apapun.");
            vbox.getChildren().add(noBooksLabel);
        } else {
            for (Book book : borrowedBooks) {
                Label bookInfo = new Label(
                        "ID Buku: " + book.getId_buku() + "\n" +
                                "Judul Buku: " + book.getJudul() + "\n" +
                                "Penulis: " + book.getAuthor() + "\n" +
                                "Kategori: " + book.getCategory() + "\n" +
                                "Durasi: " + book.getDuration() + " hari\n" +
                                "-----------------------------"
                );
                vbox.getChildren().add(bookInfo);
            }

            Button returnButton = new Button("Kembalikan Semua Buku");
            returnButton.getStyleClass().add("button");
            returnButton.setOnAction(e -> {
                for (Book book : borrowedBooks) {
                    book.setStockBuku(book.getStockBuku() + 1);
                }
                borrowedBooks.clear();
                showAlert(Alert.AlertType.INFORMATION, "Pengembalian buku berhasil dilakukan.");
                menuStudent();
            });

            vbox.getChildren().add(returnButton);
        }

        Button backButton = new Button("Kembali ke Menu Mahasiswa");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> menuStudent());

        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 500, 500);
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }

    @Override
    public void displayBooks() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("vbox");

        Label title = new Label("===== Daftar Buku =====");
        title.getStyleClass().add("label-title");
        vbox.getChildren().add(title);

        ArrayList<Book> bookList = LibrarySystem.getBookList();
        if (bookList.isEmpty()) {
            Label noBooksLabel = new Label("Tidak ada buku yang tersedia.");
            vbox.getChildren().add(noBooksLabel);
        } else {
            for (Book book : bookList) {
                Label bookInfo = new Label(
                        "ID Buku: " + book.getId_buku() + "\n" +
                                "Judul Buku: " + book.getJudul() + "\n" +
                                "Penulis: " + book.getAuthor() + "\n" +
                                "Kategori: " + book.getCategory() + "\n" +
                                "Stok Buku: " + book.getStockBuku() + "\n" +
                                "-----------------------------"
                );
                vbox.getChildren().add(bookInfo);
            }
        }

        Button backButton = new Button("Kembali ke Menu Mahasiswa");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> menuStudent());

        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 414, 896);
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }

    @Override
    public void menu() {
        menuStudent();
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
