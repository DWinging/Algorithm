package BaekJoon;

import java.io.*;

public class BaekJoon_1759 {

    static StringBuilder sb = new StringBuilder();
    static int[] str, value;
    static char[] target = {'a', 'e', 'i', 'o', 'u'};
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        inputArray(m);
        value = new int[n];
        solve(n, m, 0, 0, 0);
        System.out.print(sb);
    }

    private static void inputArray(int m) throws IOException {
        int[] check = new int['z' + 1];
        for(int i = 0; i < m; i++) {
            while(c <= ' ') c = System.in.read();
            check[c] = 1;
            c = System.in.read();
        }

        for(int i : target) check[i] *= -1;

        str = new int[m];
        for(int i = 'a', idx = 0; i <= 'z'; i++) {
            if(check[i] != 0) str[idx++] = check[i] * i;
        }
    }

    private static void solve(int n, int m, int idx, int stack, int total) {
        if (total == n) {
            if(stack >= 1 && stack + 2 <= n) {
                for(int i : value) sb.append((char) i);
                sb.append('\n');
            }
            return;
        }

        for(int i = idx; i <= m - (n - total); i++) {
            value[total] = Math.abs(str[i]);
            solve(n, m, i + 1, str[i] < 0 ? stack + 1 : stack, total + 1);
        }
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
