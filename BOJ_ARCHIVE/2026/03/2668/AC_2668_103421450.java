/**
 * [BOJ] 2668 - 숫자고르기
 * - 제출 날짜: 2026년 3월 2일
 * - 결과: 맞았습니다!!
 * - 메모리: 11444 KB
 * - 시간: 60 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int c, arr[];
    static int[] select;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);
        System.out.print(solve(n) + "\n" + buildString(n));
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n + 1];
        select = new int[n + 1];
        for(int i = 1; i <= n; i++) arr[i] = readInt();
    }

    private static int solve(int n) {
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(select[i] > 0) continue;
            int cycle = checkCycle(i, 0, i);
            if(cycle > 0) cnt += cycle;
            else checkCycle(i, i, 0);
        }
        return cnt;
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

    private static String buildString(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            if(select[i] > 0) sb.append(i).append('\n');
        }
        return sb.toString();
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
