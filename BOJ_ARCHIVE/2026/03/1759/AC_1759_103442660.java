/**
 * [BOJ] 1759 - 암호 만들기
 * - 제출 날짜: 2026년 3월 2일
 * - 결과: 맞았습니다!!
 * - 메모리: 11484 KB
 * - 시간: 68 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

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
        boolean[] check = new boolean['z' + 1];
        for(int i = 0; i < m; i++) {
            while(c <= ' ') c = System.in.read();
            check[c] = true;
            c = System.in.read();
        }

        str = new int[m];
        for(int i = 'a', idx = 0; i <= 'z'; i++) {
            if(check[i]) {
                boolean flag = check((char) i);
                str[idx++] = flag ? -i : i;
            }
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
        if(idx == m) return;

        value[total] = Math.abs(str[idx]);
        solve(n, m, idx + 1, str[idx] < 0 ? stack + 1 : stack, total + 1);
        solve(n, m, idx + 1, stack, total);
    }

    private static boolean check(char idx) {
        for(char i : target) {
            if(i == idx) return true;
        }
        return false;
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
