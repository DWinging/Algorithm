/**
 * [BOJ] 13418 - 학교 탐방하기
 * - 제출 날짜: 2026년 3월 15일
 * - 결과: 맞았습니다!!
 * - 메모리: 50800 KB
 * - 시간: 316 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<int[]>[] edge;
    static int[] dist, dist2;
    static boolean[] visited;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        inputEdge(m);
        int res1 = solve(n);
        int res2 = solve2(n);
        System.out.println(res2 - res1);
    }

    private static void init(int n) {
        edge = new ArrayList[n + 1];
        dist = new int[n + 1];
        dist2 = new int[n + 1];
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++) {
            edge[i] = new ArrayList<>();
            dist[i] = 2;
            dist2[i] = -1;
        }
    }

    private static void inputEdge(int m) throws IOException {
        while(m-- >= 0) {
            int v1 = readInt();
            int v2 = readInt();
            int type = readInt() ^ 1;

            edge[v1].add(new int[]{v2, type});
            edge[v2].add(new int[]{v1, type});
        }
    }

    private static int solve(int n) {
        int d = 0, u = 0, cnt = 0;
        visited[0] = true;

        while(cnt < n) {
            for(int[] cur : edge[u]) {
                int next = cur[0];
                int w = cur[1];
                if(!visited[next] && dist[next] > w)
                    dist[next] = w;
            }

            int v = 2;
            for(int i = 1; i <= n; i++) {
                if(!visited[i] && dist[i] < v) {
                    v = dist[i];
                    u = i;
                }
            }

            visited[u] = true;
            d += v;
            cnt++;
        }

        return d * d;
    }

    private static int solve2(int n) {
        int d = 0, u = 0, cnt = 0;
        visited[0] = false;

        while(cnt < n) {
            for(int[] cur : edge[u]) {
                int next = cur[0];
                int w = cur[1];
                if(visited[next] && dist2[next] < w) {
                    dist2[next] = w;
                }
            }

            int v = -1;
            for(int i = 1; i <= n; i++) {
                if(visited[i] && dist2[i] > v) {
                    v = dist2[i];
                    u = i;
                }
            }

            visited[u] = false;
            d += v;
            cnt++;
        }
        return d * d;
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
