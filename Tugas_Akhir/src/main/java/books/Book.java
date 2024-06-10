package books;

public class Book {

  private String id_buku;
  private String judul;
  private String author;
  private String category;
  private int stockBuku;
  private int duration;

  public Book(String id_buku, String judul, String author, String category, int stockBuku, int duration) {
    this.id_buku = id_buku;
    this.judul = judul;
    this.author = author;
    this.category = category;
    this.stockBuku = stockBuku;
    this.duration = duration;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public void setId_buku(String id_buku) {
    this.id_buku = id_buku;
  }

  public void setJudul(String judul) {
    this.judul = judul;
  }

  public void setStockBuku(int stockBuku) {
    this.stockBuku = stockBuku;
  }

  public String getAuthor() {
    return author;
  }

  public String getCategory() {
    return category;
  }

  public int getDuration() {
    return duration;
  }

  public String getId_buku() {
    return id_buku;
  }

  public String getJudul() {
    return judul;
  }

  public int getStockBuku() {
    return stockBuku;
  }
}
