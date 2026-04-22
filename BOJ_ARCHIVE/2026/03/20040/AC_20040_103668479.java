/**
 * [BOJ] 20040 - 사이클 게임
 * - 제출 날짜: 2026년 3월 9일
 * - 결과: 맞았습니다!!
 * - 메모리: 15900 KB
 * - 시간: 232 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int[] parents; // 부모를 저장할 배열
    static int[] cnt;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        System.out.println(solve(n, m));
    }

    // 부모 저장, 0-based 배열 사용
    private static void init(int n) {
        parents = new int[n];
        cnt = new int[n];
        for(int i = 0; i < n; i++) {
        	parents[i] = i;
        	cnt[i] = 1;
        }
    }
    
    // 문제 풀이
    private static int solve(int n, int m) throws IOException {
        for(int t = 1; t <= m; t++) {
            int pA = find(readInt());
            int pB = find(readInt());
            
            // 부모가 같다면 같은 집합으로 종료
            if(pA == pB) return t;
            union(pA, pB);
        }
        return 0;
    }
    
    // Union
    private static void union(int pA, int pB) {
        if(cnt[pA] >= cnt[pB]) {
            parents[pB] = pA;
            cnt[pA] += cnt[pB];
        } else {
            parents[pA] = pB;
            cnt[pB] += cnt[pA];
        }
    }
    
    // 부모 탐색
    private static int find(int p) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p]);
    }
    
    //FastIO
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