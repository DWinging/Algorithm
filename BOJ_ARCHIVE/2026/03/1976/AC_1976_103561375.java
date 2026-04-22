/**
 * [BOJ] 1976 - 여행 가자
 * - 제출 날짜: 2026년 3월 6일
 * - 결과: 맞았습니다!!
 * - 메모리: 12032 KB
 * - 시간: 80 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int[] parents;
    static int c;
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        System.out.println(solve(m));
    }

    private static void init(int n) throws IOException {
        parents = new int[n];
        for(int i = 0; i < n; i++) parents[i] = i;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int num = readInt();
                if(i < j && num == 1) union(i, j);
            }
        }
    }

    private static String solve(int m) throws IOException {
        int from = find(readInt() - 1);
        while(m-- > 1) {
            int to = find(readInt() - 1);
            if(from != to) return "NO";
            from = to;
        }
        return "YES";
    }

    private static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if(pA != pB) {
            if(pA < pB) parents[pB] = pA;
            else parents[pA] = pB;
        }
    }

    private static int find(int p) {
        if(p == parents[p]) return p;
        else return parents[p] = find(parents[p]);
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