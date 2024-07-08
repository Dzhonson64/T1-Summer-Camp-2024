package ru.t1.summer.camp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter e-mail of java developer for registration: ");
        String email = scanner.next();

        App app = new App(email);
        app.Register();

        scanner.close();
    }
}