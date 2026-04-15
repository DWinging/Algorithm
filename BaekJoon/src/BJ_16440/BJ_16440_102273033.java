package BJ_16440;

import java.io.IOException;
/**
 * 2026년 1월 24일 풀이
 * BaekJoon_16440 제이크와 케이크
 * 메모리 12516 KB
 * 시간 100 ms
 */
public class BJ_16440_102273033 {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        char[] arr = readString(n);

        int l = 0, r, s = 0, k = 0;
        for(r = 0; r < n / 2; r++) {
            if('s' == arr[r]) s++;
            else k++;
        }

        while(r < n && s != k) {
            if(arr[r++] == 's') s++;
            else k++;

            if(arr[l++] == 's') s--;
            else k--;
        }

        if(l == 0 || r == n) {
            System.out.println(1 + "\n" + n / 2);
        }
        else {
            System.out.println(2);
            System.out.println(l + " " + r);
        }
    }

    public static int readInt() throws IOException {
        int c = System.in.read();

        while (c <= ' ') {
            c = System.in.read();
        }

        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n * 10) + (c - '0');
            c = System.in.read();
        }

        return n;
    }

    public static char[] readString(int n) throws IOException {
        int c = System.in.read();

        while (c <= ' ') {
            c = System.in.read();
        }

        char[] arr = new char[n];
        int i = 0;
        while(c > ' ' && i < n) {
            arr[i++] = (char)c;
            c = System.in.read();
        }
        return arr;
    }
}
