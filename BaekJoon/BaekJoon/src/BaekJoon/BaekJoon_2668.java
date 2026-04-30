package BaekJoon;

import java.io.*;

public class BaekJoon_2668 {

    final static int INF = 105;
    static int[] arr, select;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);
        solve(n);
        System.out.print(buildString(n));
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n + 1];
        select = new int[n + 1];
        for(int i = 1; i <= n; i++) arr[i] = readInt();
    }

    private static void solve(int n) {
        for(int i = 1; i <= n; i++) {
            if(select[i] > 0) continue;
            checkCycle(i, i);
        }
    }

    private static void checkCycle(int num, int mark) {
        int idx = num;
        while(select[idx] == 0) {
            select[idx] = mark;
            idx = arr[idx];
        }

        if(select[idx] == mark) {
            int start = idx;
            idx = arr[start];
            select[idx] = INF;
            while(idx != start) {
                idx = arr[idx];
                select[idx] = INF;
            }
        }
    }

    private static String buildString(int n) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(select[i] == INF) {
                sb.append(i).append('\n');
                cnt++;
            }
        }
        return cnt + "\n" + sb;
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
