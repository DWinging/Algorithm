package BaekJoon;

import java.util.Scanner;

public class BaekJoon_2588 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        int a3 = a * (b / 100);
        int n4 = b % 100;
        int a4 = a * (n4 / 10);
        int a5 = a * (n4 % 10);

        System.out.println(a5);
        System.out.println(a4);
        System.out.println(a3);
        System.out.println(a*b);
    }
}
