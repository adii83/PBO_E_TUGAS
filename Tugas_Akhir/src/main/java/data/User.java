package data;

import books.Book;

public class User {

    private String nama;
    private String NIM;
    private String fakultas;
    private String prodi;

    public User(String nama, String NIM, String fakultas, String prodi) {
        this.nama = nama;
        this.NIM = NIM;
        this.fakultas = fakultas;
        this.prodi = prodi;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getFakultas() {
        return fakultas;
    }

    public String getNIM() {
        return NIM;
    }

    public String getNama() {
        return nama;
    }

    public String getProdi() {
        return prodi;
    }
}
