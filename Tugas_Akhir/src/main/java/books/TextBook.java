package books;

public class TextBook extends Book {

  public TextBook(String id_buku, String judul, String author, String category, int stockBuku, int duration) {
    super(id_buku, judul, author, "Novel", stockBuku, duration);
  }
}
