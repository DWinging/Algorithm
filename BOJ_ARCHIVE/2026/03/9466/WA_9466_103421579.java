/**
 * [BOJ] 9466 - 텀 프로젝트
 * - 제출 날짜: 2026년 3월 2일
 * - 결과: 시간 초과
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int TOTAL = 100_000;
    static int[] arr, select;
    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        init();
        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            inputArray(n);
            sb.append(solve(n)).append('\n');
        }
        System.out.print(sb);
    }

    private static void init() throws IOException{
        c = System.in.read();
        arr = new int[TOTAL + 1];
        select = new int[TOTAL + 1];
    }

    private static void inputArray(int n) throws IOException {
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
            select[i] = 0;
        }
    }

    private static int solve(int n) {
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(select[i] > 0) continue;
            int cycle = checkCycle(i, 0, i);
            if(cycle > 0) cnt += cycle;
            else checkCycle(i, i, 0);
        }
        return n - cnt;
    }

    private static int checkCycle(int num, int w, int mark) {
        int cnt = 0;
        int idx = num;
        while(select[idx] == w) {
            select[idx] = mark;
            idx = arr[idx];
            cnt++;
        }
        return num == idx ? cnt : 0;
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
