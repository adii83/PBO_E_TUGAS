package com.tugas;

import java.util.*;

public class Main {
    static Scanner Input = new Scanner(System.in);
    public static void main(String[] args) {
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
                    inputNim();
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

    private static void inputNim() {

        System.out.print("Masukan NIM : ");
        String nim= Input.next();
        if (nim.length()!=15){
            System.out.println("User Not Found!!");
        }
        else {
            System.out.println("Successfull Login As Student!! ");
        }
    }

    private static void loginAdmin() {

        System.out.print("Masukan Username (admin) : ");
        String username = Input.next();
        Input.nextLine();
        System.out.print("Masukan Password (admin) : ");
        String pw = Input.next();

        if (username.equals("admin") && pw.equals("admin")){
            System.out.println("Succesfull Login As Admin!!");
        }else {
            System.out.println("Admin User Not Found!!");
        }

    }

    private static void exit(){
        System.out.println("Terima Kasih!!!");
        System.exit(0);
    }

}
