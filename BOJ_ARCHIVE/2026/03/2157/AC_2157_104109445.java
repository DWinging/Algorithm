/**
 * [BOJ] 2157 - 여행
 * - 제출 날짜: 2026년 3월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 13448 KB
 * - 시간: 164 ms
 */

import java.io.*;
import java.util.*;

class Main {
    static int[][] edge; // 인접 행렬 (또는 간선이 적다면 ArrayList<int[]>[] 추천)
    static int[][] dp;
    static int[] indegree;
    static int N, M, K, c;

    // Custom Queue (배열 기반)
    static int[] queue;
    static int head = 0, tail = 0;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        K = readInt();

        edge = new int[N + 1][N + 1];
        dp = new int[N + 1][M + 1];
        indegree = new int[N + 1];
        queue = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < K; i++) {
            int u = readInt();
            int v = readInt();
            int w = readInt();
            if (u < v) { // DAG 보장 조건
                if (edge[u][v] < w) {
                    if (edge[u][v] == 0) indegree[v]++; // 처음 연결될 때만 진입차수 증가
                    edge[u][v] = w;
                }
            }
        }

        solve();

        int maxCost = 0;
        for (int i = 1; i <= M; i++) {
            maxCost = Math.max(maxCost, dp[N][i]);
        }
        System.out.println(maxCost);
    }

    private static void solve() {
        dp[1][1] = 0;
        
        // 시작점 찾기 (진입 차수가 0인 노드들)
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue[tail++] = i;
            }
        }

        while (head < tail) {
            int curr = queue[head++];

            for (int next = curr + 1; next <= N; next++) {
                if (edge[curr][next] == 0) continue;

                // DP 갱신: 현재 노드(curr)에서 갈 수 있는 모든 경로 계산
                for (int cnt = 1; cnt < M; cnt++) {
                    if (dp[curr][cnt] != -1) {
                        dp[next][cnt + 1] = Math.max(dp[next][cnt + 1], dp[curr][cnt] + edge[curr][next]);
                    }
                }

                // 위상 정렬: 진입 차수 감소 및 0이 되면 큐에 삽입
                if (--indegree[next] == 0) {
                    queue[tail++] = next;
                }
            }
        }
    }

    private static int readInt() throws IOException {
        int n = 0;
        c = System.in.read();
        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}