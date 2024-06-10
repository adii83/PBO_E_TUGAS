package books;

public class HistoryBook extends Book {

  public HistoryBook(String id_buku, String judul, String author, String category, int stockBuku, int duration) {
    super(id_buku, judul, author, "Sejarah", stockBuku, duration);
  }
}
