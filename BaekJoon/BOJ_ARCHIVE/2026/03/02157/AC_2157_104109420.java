/**
 * [BOJ] 2157 - 여행
 * - 제출 날짜: 2026년 3월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 12648 KB
 * - 시간: 144 ms
 */

import java.io.*;
import java.util.*;

class Main {

    static int[][] dp, edge;
    static int c, N, M, K;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        N = readInt();
        M = readInt();
        K = readInt();
        init();
        inputRoute();
        solve();
        System.out.println(getMaxCost());
    }

    private static void init() {
        edge = new int[N + 1][N + 1];
        dp = new int[N + 1][M + 1];
        for(int i = 1; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[1][1] = 0;
    }

    private static void inputRoute() throws IOException {
        int k = K;
        while(k-- > 0) {
            int from = readInt();
            int to = readInt();
            int cost = readInt();
            if(edge[from][to] < cost) {
                edge[from][to] = cost;
            }
        }
    }

    private static void solve() {            
        for(int from = 1; from < N; from++) {
            for(int to = from + 1; to <= N; to++) {                
                if(edge[from][to] == 0) continue;               
                for(int cnt = 1; cnt < M; cnt++) {
                    if(dp[from][cnt] == -1) continue;
                    
                    int nextCost = dp[from][cnt] + edge[from][to];
                    if(dp[to][cnt + 1] < nextCost) {
                        dp[to][cnt + 1] = nextCost;
                    }
                }
            }
        }
    }

    private static int getMaxCost() {
        int res = 0;
        for(int i = 1; i <= M; i++) {
            if(res < dp[N][i]) {
                res = dp[N][i];
            }
        }
        return res;
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