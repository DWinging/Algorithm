/**
 * [BOJ] 16940 - BFS 스페셜 저지
 * - 제출 날짜: 2026년 4월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 27496 KB
 * - 시간: 204 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] edge;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        inputEdge(n);
        System.out.println(bfs(n));
    }

    private static void inputEdge(int n) throws IOException {
        edge = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
        }

        while(n-- > 1) {
            int v1 = readInt();
            int v2 = readInt();

            edge[v1].add(v2);
            edge[v2].add(v1);
        }
    }

    private static int bfs(int n) throws IOException {
        int first = readInt();
        if(first != 1) return 0;

        int[] que = new int[n];
        boolean[] visited = new boolean[n + 1];
        int head = 0, tail = 0;
        que[tail++] = 1;
        visited[1] = true;

        while(head < tail) {
            int cur = que[head++];

            int cnt = 0;
            for(int i : edge[cur]) {
                if(!visited[i]) {
                    visited[i] = true;
                    cnt++;
                }
            }

            while(cnt-- > 0) {
                int node = readInt();
                if(!visited[node]) return 0;
                que[tail++] = node;
            }
        }
        return 1;
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
