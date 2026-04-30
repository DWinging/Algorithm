/**
 * [BOJ] 16940 - BFS 스페셜 저지
 * - 제출 날짜: 2026년 4월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 46672 KB
 * - 시간: 364 ms
 */

import java.util.*;
import java.io.*;

class Main {

    static HashSet<Integer>[] arr;
    static boolean[] visited;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        if (n == 0) return;

        inputEdge(n);
        System.out.println(bfs(n));
    }

    private static void inputEdge(int n) throws IOException {
        arr = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new HashSet<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int v1 = readInt();
            int v2 = readInt();
            arr[v1].add(v2);
            arr[v2].add(v1);
        }
    }

    private static int bfs(int n) throws IOException {
        int[] que = new int[n];
        int head = 0, tail = 0;
        
        int first = readInt();
        if (first != 1) return 0;

        visited = new boolean[n + 1];
        visited[1] = true;
        int p = first;
        que[tail++] = p;

        for (int i = 1; i < n; i++) {
            int cur = readInt();

            while (!arr[p].contains(cur)) {
                if (!check(p)) return 0;
                if (head + 1 >= tail) return 0;
                p = que[++head];
            }

            que[tail++] = cur;
            visited[cur] = true;
        }
        return 1;
    }

    private static boolean check(int p) {
        for (int neighbor : arr[p]) {
            if (!visited[neighbor]) return false;
        }
        return true;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}