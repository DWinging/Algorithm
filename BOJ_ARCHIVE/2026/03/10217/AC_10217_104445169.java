/**
 * [BOJ] 10217 - KCM Travel
 * - 제출 날짜: 2026년 3월 28일
 * - 결과: 맞았습니다!!
 * - 메모리: 48576 KB
 * - 시간: 1220 ms
 */

import java.util.*;
import java.io.*;

class Main {

    static ArrayList<int[]>[] edge = new ArrayList[101];
    static { for(int i = 1; i <= 100; i++) edge[i] = new ArrayList<>(); }
    
    static int[][] dp = new int[101][10_001];
    static int c, N, M, K;
    static final int INF = 1_000_000_000;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int t = readInt();
        while(t-- > 0) {
            init();
            inputEdge();
            sb.append(solve()).append('\n');            
        }
        System.out.print(sb);
    }

    private static void init() throws IOException {
        N = readInt();
        M = readInt();
        K = readInt();

        for(int i = 1; i <= 100; i++) {
            edge[i].clear();
            Arrays.fill(dp[i], INF);            
        }
    }

    private static void inputEdge() throws IOException {
        while(K-- > 0) {
            int from = readInt();
            int to = readInt();
            int cost = readInt();
            int d = readInt();

            if(cost <= M) {
                edge[from].add(new int[]{to, cost, d});
            }
        }
    }

    private static String solve() {
        dp[1][0] = 0;

        for (int cost = 0; cost <= M; cost++) {
            for (int u = 1; u <= N; u++) {
                if (dp[u][cost] == INF) continue;

                for (int[] next : edge[u]) {
                    int to = next[0];
                    int nextCost = cost + next[1];
                    int nextD = dp[u][cost] + next[2];

                    if (nextCost <= M && dp[to][nextCost] > nextD) {
                        dp[to][nextCost] = nextD;
                    }
                }
            }
        }

        int minTime = INF;
        for (int i = 0; i <= M; i++) {
            if (dp[N][i] < minTime) minTime = dp[N][i];
        }

        return minTime == INF ? "Poor KCM" : Integer.toString(minTime);
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