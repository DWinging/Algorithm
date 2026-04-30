/**
 * [BOJ] 18116 - 로봇 조립
 * - 제출 날짜: 2026년 3월 30일
 * - 결과: 맞았습니다!!
 * - 메모리: 47668 KB
 * - 시간: 548 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    final static int MAX_RANGE = 1_000_000;
    
    static int[] parents, cnt;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        init();
        System.out.print(solve(n));
    }

    private static void init() throws IOException {
        parents = new int[MAX_RANGE + 1];
        cnt = new int[MAX_RANGE + 1];
        for(int i = 1; i <= MAX_RANGE; i++) {
            parents[i] = i;
            cnt[i] = 1;
        }
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(n-- > 0) {
            char command = readChar();
            if(command == 'I') {
                int partA = readInt();
                int partB = readInt();
                union(partA, partB);
            } else {
                int part = readInt();
                int p = find(part);
                sb.append(cnt[p]).append('\n');
            }
        }
        return sb.toString();
    }

    private static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if(pA != pB) {
            if(cnt[pA] >= cnt[pB]) {
                parents[pB] = pA;
                cnt[pA] += cnt[pB];
                cnt[pB] = 0;
            } else {
                parents[pA] = pB;
                cnt[pB] += cnt[pA];
                cnt[pA] = 0;
            }
        }
    }

    private static int find(int p) {
        if(p == parents[p]) return p;
        else return parents[p] = find(parents[p]);
    }


    //Fast IO
    private static char readChar() throws IOException {
        while(c <= ' ') c = System.in.read();
        char str = (char) c;
        c = System.in.read();
        return str;
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