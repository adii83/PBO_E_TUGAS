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
import exception.custom.illegalAdminAccess;

import java.util.ArrayList;
import java.util.UUID;
import java.io.File;

public class Admin extends User implements iMenu {

    private Stage primaryStage;
    private ArrayList<Student> userStudent = LibrarySystem.getUserStudent();
    private ArrayList<Book> bookList = LibrarySystem.getBookList();

    public Admin(String nama, String NIM, String fakultas, String prodi) {
        super(nama, NIM, fakultas, prodi);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public LibrarySystem librarySystem = new LibrarySystem();

    public void menuAdmin() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        vbox.getStyleClass().add("vbox");

        Label title = new Label("===== Dashboard Admin =====");
        title.getStyleClass().add("label-title");
        Button addStudentButton = new Button("Tambah Mahasiswa");
        addStudentButton.getStyleClass().add("button");
        Button addBookButton = new Button("Tambah Buku");
        addBookButton.getStyleClass().add("button");
        Button displayStudentsButton = new Button("Tampilkan Daftar Mahasiswa");
        displayStudentsButton.getStyleClass().add("button");
        Button displayBooksButton = new Button("Daftar Buku Tersedia");
        displayBooksButton.getStyleClass().add("button");
        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("button");

        addStudentButton.setOnAction(e -> addStudent());
        addBookButton.setOnAction(e -> inputBook());
        displayStudentsButton.setOnAction(e -> displayStudent());
        displayBooksButton.setOnAction(e -> displayBooks());
        logoutButton.setOnAction(e -> {
            LibrarySystem librarySystem = new LibrarySystem();
            librarySystem.start(primaryStage);
        });

        vbox.getChildren().addAll(title, addStudentButton, addBookButton, displayStudentsButton, displayBooksButton, logoutButton);

        Scene scene = new Scene(vbox, 414, 896); // Ukuran disesuaikan untuk smartphone
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }

    public void addStudent() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("vbox");

        Button addButton = new Button("Tambah");
        addButton.getStyleClass().add("button");
        Label nameLabel = new Label("Nama:");
        TextField nameField = new TextField();
        nameField.getStyleClass().add("text-field");
        Label nimLabel = new Label("NIM:");
        TextField nimField = new TextField();
        nimField.getStyleClass().add("text-field");
        Label fakultasLabel = new Label("Fakultas:");
        TextField fakultasField = new TextField();
        fakultasField.getStyleClass().add("text-field");
        Label prodiLabel = new Label("Prodi:");
        TextField prodiField = new TextField();
        prodiField.getStyleClass().add("text-field");


        addButton.setOnAction(e -> {
            String nama = nameField.getText();
            String NIM = nimField.getText();
            String fakultas = fakultasField.getText();
            String prodi = prodiField.getText();

            if (NIM.length() != 15) {
                showAlert(Alert.AlertType.ERROR, "NIM harus 15 digit angka.");
            } else {
                Student mahasiswa = new Student(nama, NIM, fakultas, prodi);
                userStudent.add(mahasiswa);
                showAlert(Alert.AlertType.INFORMATION, "Mahasiswa berhasil ditambahkan.");
            }
        });

        vbox.getChildren().addAll( addButton,nameLabel, nameField, nimLabel, nimField, fakultasLabel, fakultasField, prodiLabel, prodiField);

        Button backButton = new Button("Kembali ke Menu Admin");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> menuAdmin());

        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 414, 896); // Ukuran disesuaikan untuk smartphone
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }

    public void displayStudent() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("vbox");

        Label title = new Label("===== Daftar Mahasiswa =====");
        title.getStyleClass().add("label-title");
        vbox.getChildren().add(title);

        if (userStudent.isEmpty()) {
            Label noStudentLabel = new Label("Tidak ada mahasiswa yang terdaftar.");
            vbox.getChildren().add(noStudentLabel);
        } else {
            for (Student student : userStudent) {
                if (student != null) {
                    Label studentInfo = new Label(
                            "Nama: " + student.getNama() + "\n" +
                                    "NIM: " + student.getNIM() + "\n" +
                                    "Fakultas: " + student.getFakultas() + "\n" +
                                    "Prodi: " + student.getProdi() + "\n" +
                                    "-----------------------------"
                    );
                    vbox.getChildren().add(studentInfo);
                }
            }
        }

        Button backButton = new Button("Kembali ke Menu Admin");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> menuAdmin());

        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 414, 896); // Ukuran disesuaikan untuk smartphone
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }

    public void inputBook() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("vbox");

        Label title = new Label("===== Tambah Buku =====");
        title.getStyleClass().add("label-title");
        Label categoryLabel = new Label("Pilih kategori buku:");
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Story Book", "History Book", "Text Book");
        categoryComboBox.setValue("Story Book");

        Label titleLabel = new Label("Judul Buku:");
        TextField titleField = new TextField();
        titleField.getStyleClass().add("text-field");
        Label authorLabel = new Label("Penulis:");
        TextField authorField = new TextField();
        authorField.getStyleClass().add("text-field");
        Label stockLabel = new Label("Stok Buku:");
        TextField stockField = new TextField();
        stockField.getStyleClass().add("text-field");
        Button addButton = new Button("Tambah");
        addButton.getStyleClass().add("button");

        addButton.setOnAction(e -> {
            String judul = titleField.getText();
            String author = authorField.getText();
            int stok = Integer.parseInt(stockField.getText());
            String category = categoryComboBox.getValue();

            Book buku = null;
            switch (category) {
                case "Story Book":
                    buku = new StoryBook(generateId(), judul, author, "Cerita", stok, 0);
                    break;
                case "History Book":
                    buku = new HistoryBook(generateId(), judul, author, "Sejarah", stok, 0);
                    break;
                case "Text Book":
                    buku = new TextBook(generateId(), judul, author, "Teknik", stok, 0);
                    break;
            }

            if (buku != null) {
                bookList.add(buku);
                showAlert(Alert.AlertType.INFORMATION, "Buku berhasil ditambahkan.");
            }
        });

        vbox.getChildren().addAll(categoryLabel, categoryComboBox, titleLabel, titleField, authorLabel, authorField, stockLabel, stockField, addButton);

        Button backButton = new Button("Kembali ke Menu Admin");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> menuAdmin());

        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 500, 500);
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }


    public void displayBooks() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("vbox");

        Label title = new Label("===== Daftar Buku =====");
        title.getStyleClass().add("label-title");
        vbox.getChildren().add(title);

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

        Button backButton = new Button("Kembali ke Menu Admin");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> menuAdmin());

        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 500, 500);
        scene.getStylesheets().add(new File("src/css/styles.css").toURI().toString());
        primaryStage.setScene(scene);
    }

    @Override
    public void menu() {
        menuAdmin();
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean isAdmin(String username, String password) throws illegalAdminAccess {
        if ("admin".equals(username) && "admin".equals(password)) {
            return true;
        } else {
            throw new illegalAdminAccess("Invalid admin credentials");
        }
    }
}
