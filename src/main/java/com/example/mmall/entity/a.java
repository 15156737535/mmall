package com.example.mmall.entity;

public class a {

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            for (int o = i; o < 10; o++) {
                System.out.print(' ');
            }
            for (int j = 0; j < (i - 1) * 2 + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int a = 9; a > 0; a--) {
            for (int b = a; b < 10; b++) {
                System.out.print(' ');
            }
            for (int c = 0; c < (a - 1) * 2 + 1; c++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
