package books;

public class StoryBook extends Book {

  public StoryBook(String id_buku, String judul, String author, String category, int stockBuku, int duration) {
    super(id_buku, judul, author, "Cerita", stockBuku, duration);
  }
}
