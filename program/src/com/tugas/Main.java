package com.tugas;

import java.util.Scanner;
import java.util.ArrayList;


class User{
    String nama;
    String NIM;
    String fakultas;
    String prodi;

    User ( String nama,String NIM,String fakultas,String prodi){
        this.nama=nama;
        this.NIM=NIM;
        this.fakultas=fakultas;
        this.prodi=prodi;
    }
}

class Buku{
    String id_buku;
    String judul;
    String author;
    String category;
    int stockBuku;

    Buku(String id_buku,String judul,String author,String category,int stockBuku){
        this.id_buku=id_buku;
        this.judul=judul;
        this.author=author;
        this.category=category;
        this.stockBuku=stockBuku;
    }

}



public class Main {
    static Scanner Input = new Scanner(System.in);
    static ArrayList<User> userStudent= new ArrayList<>();
    static ArrayList<Buku> bookList= new ArrayList<>();
    public static void main(String[] args) {
        userStudent.add(new User("Slamet Hariyadi", "202310370311221", "Teknik","Informatika"));
        userStudent.add(new User("Bagus Romadhon", "202310370311251", "Teknik", "Informatika"));
        userStudent.add(new User("Rifqi Maulana Ishak", "202310370311252", "Teknik", "Informatika"));

        bookList.add(new Buku("388c-e681-9152", "Pemograman Java OOP", "Anan", "Ilmu Pengetahuan", 10));
        bookList.add(new Buku("ed90-be30-5cdb", "Laskar Pelangi", "Raditya Tantra", "Fiksi", 20));
        bookList.add(new Buku("d95e-0c4a-9523", "Matematika Diskrit", "Rakan", "Ilmu Pengetahuan", 30));
        
        menu();
    }
        
    static void menu(){
        int pilihan;
        do {
            System.out.println("====== Library System ======");
            System.out.println("1. Login Sebagai Mahasiswa");
            System.out.println("2. Login Sebagai Admin");
            System.out.println("3. Exit");
            System.out.print("Pilih Opsi (1-3): ");
            pilihan= Input.nextInt();

            switch (pilihan) {
                case 1:
                    loginStudent();
                    break;
                case 2:
                    loginAdmin();
                    break;
                case 3:
                    exit();
                default:
                    System.out.println("Pilihan Tidak Valid!!\nPilih Nomor (1-3) !!!");
            }
        }while (pilihan !=3);

    }


    static void loginStudent() {

        System.out.print("Masukan NIM : ");
        String NIM= Input.next();
            if (checkNim(NIM)){
            menuStudent();
            }else {
            System.out.println("User Not Found!! ");
        }
    }    
 
    static boolean checkNim(String NIM){
        for (User student : userStudent) {
            if (student.NIM.equals(NIM)) {
                return true;
            }
        }    
        return false; 
    }

    static void menuStudent(){
        Student student = new Student();
        int pilihan;
        do{
            System.out.println("====== Student Menu ======");
            System.out.println("1. Buku Terpinjam");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Log Out");
            System.out.print("Pilih Opsi (1-3) : ");
            pilihan = Input.nextInt();
            System.out.print("\n");

            switch(pilihan) {
                case 1:
                    //Code Buku Terpinjam
                    break;
                case 2:    
                    student.displayBooks();
                    System.out.println("Input ID Buku yang Ingin diPinjam (Ketik 99 Untuk Kembali ke-Menu Awal)");
                    System.out.print("Input : "); 
                    String id = Input.next();
                   
                    if (id.equals("99")) {
                        System.out.println("Kembali ke Menu Awal...");
                        menuStudent();                        
                    }
                    
                    break;
                case 3:
                    System.out.println("System Logout...");
                    menu();
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid!!\nPilih Nomor (1-3) !!!");
                    
            }
        }while(pilihan!=3);

    }

    static void loginAdmin() {

        System.out.print("Masukan Username (admin) : ");
        String username = Input.next();
        Input.nextLine();
        System.out.print("Masukan Password (admin) : ");
        String pw = Input.next();

        if (username.equals("admin") && pw.equals("admin")){
            menuAdmin();
        }else {
            System.out.println("Admin User Not Found!!");
        }

    }

    static void menuAdmin(){
        Admin admin = new Admin();
        int pilihan;
        do {
            System.out.println("===== Dashboard Admin =====");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tampilkan Daftar Mahasiswa");
            System.out.println("3. Logout");
            System.out.print("Pilih Opsi (1-3): ");
            pilihan = Input.nextInt();

            switch (pilihan) {
                case 1:
                    admin.addStudent();
                    break;
                case 2:
                    admin.displayStudent();
                    break;
                case 3:
                    System.out.println("System Logout...");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid!!\nPilih Nomor (1-3) !!!");
            }
        } while (pilihan != 3);
        System.out.println("");
     }

    static void exit(){
        System.out.println("Terima Kasih!!!");
        System.exit(0);
    }

}

class Student{
    void displayBooks(){
        System.out.println("===== Daftar Buku =====");
        for (Buku buku : Main.bookList) {
            System.out.println("ID Buku    : " + buku.id_buku);
            System.out.println("Judul Buku : " + buku.judul);
            System.out.println("Author     : " + buku.author);
            System.out.println("Category   : " + buku.category);
            System.out.println("Stok Buku  : " + buku.stockBuku);
            System.out.println("---------------------------------");
        }
    }
}

class Admin{
    Scanner Input = new Scanner(System.in);
    String nama;
    String NIM;
    String fakultas;
    String prodi;

    void addStudent(){
        System.out.print("Nama     : ");
        nama = Input.nextLine();
        do {
            System.out.print("NIM      : ");
            NIM = Input.next();
            if(NIM.length()!=15)
              System.out.println("NIM harus 15 digit !!!");
            }while(NIM.length()!=15);
        System.out.print("Fakultas : ");
        fakultas = Input.next();
        System.out.print("Prodi    : ");
        prodi = Input.next();

        User mahasiswa = new User(nama, NIM, fakultas, prodi);
        //Code tambah mahasiswa
    }

    void displayStudent(){
        System.out.println("===== Daftar Mahasiswa =====");
        for (User mahasiswa : Main.userStudent) {
            System.out.println("Nama     : " + mahasiswa.nama);
            System.out.println("NIM      : " + mahasiswa.NIM);
            System.out.println("Fakultas : " + mahasiswa.fakultas);
            System.out.println("Prodi    : " + mahasiswa.prodi);
            System.out.println("---------------------------------");
        }
    }
}
