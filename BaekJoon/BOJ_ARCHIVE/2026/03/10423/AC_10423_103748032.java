/**
 * [BOJ] 10423 - 전기가 부족해
 * - 제출 날짜: 2026년 3월 11일
 * - 결과: 맞았습니다!!
 * - 메모리: 16156 KB
 * - 시간: 140 ms
 */

import java.io.*;
import java.util.*;

public class Main {
	
    static int[] parents;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        int k = readInt();

        init(n, k);
        System.out.println(solve(n, m, k));
    }

    private static void init(int n, int k) throws IOException {
        parents = new int[n + 1];
        for(int i = 1; i <= n; i++) parents[i] = i;
        
        while(k-- > 0) parents[readInt()] = 0;
    }

    private static int solve(int n, int m, int k) throws IOException {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        while(m-- > 0) {
            int n1 = readInt();
            int n2 = readInt();
            int w = readInt();
            pq.add((((long)w << 20) | (n1 << 10) | n2));   
        }

        int cnt = n - k, res = 0;
        while(cnt > 0) {
            long cur = pq.poll();
            int pA = find((int)(cur >> 10) & ((1 << 10) - 1));
            int pB = find((int)cur & ((1 << 10) - 1));

            if(pA != pB) {
                union(pA, pB);
                res += (int)cur >> 20;
                cnt--;
            }
        }
        return res;
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p]);
    }

    private static void union(int pA, int pB) {
        if(pA < pB) parents[pB] = pA;
        else parents[pA] = pB;
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