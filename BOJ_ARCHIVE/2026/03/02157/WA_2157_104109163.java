/**
 * [BOJ] 2157 - 여행
 * - 제출 날짜: 2026년 3월 20일
 * - 결과: 시간 초과
 */

import java.io.*;
import java.util.*;

class Main {

    static ArrayList<int[]>[] edge;
    static int[][] dp;
    static int c, N, M, K, maxCost = 0;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        N = readInt();
        M = readInt();
        K = readInt();
        init();
        inputRoute();
        dfs(1, 0);
        System.out.println(maxCost);
    }

    private static void init() {
        edge = new ArrayList[N];
        for(int i = 1; i < N; i++) {
            edge[i] = new ArrayList<>();
        }

        dp = new int[N + 1][M + 1];
    }

    private static void inputRoute() throws IOException {
        int k = K;
        while(k-- > 0) {
            int from = readInt();
            int to = readInt();
            int cost = readInt();
            if(from < to) {
                edge[from].add(new int[] {to, cost});
            }
        }
    }

    private static void dfs(int idx, int cnt) {
        if(cnt >= M) return;
        if(idx == N) {
            maxCost = Math.max(maxCost, dp[idx][cnt]);
            return;
        }
            
        for(int[] next : edge[idx]) {
            if(dp[idx][cnt] + next[1] > dp[next[0]][cnt + 1]) {
                dp[next[0]][cnt + 1] = dp[idx][cnt] + next[1];
                dfs(next[0], cnt + 1);
            }
        }
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' &&  c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}