/**
 * [BOJ] 17471 - 게리맨더링
 * - 제출 날짜: 2026년 2월 17일
 * - 결과: 맞았습니다!!
 * - 메모리: 11728 KB
 * - 시간: 60 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static boolean[][] graph;
    static int[] people, visited, deque;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        init(n);

        int total = inputPeople(n);
        inputEdge(n);
        System.out.println(solve(total, n));
    }

    private static void init(int n) {
        graph = new boolean[n][n];
        people = new int[n];
        visited = new int[n];
        deque = new int[n];
    }

    private static int inputPeople(int n) throws IOException {
        int total = 0;
        for(int i = 0; i < n; i++) {
            int p = readInt();
            people[i] = p;
            total += p;
        }
        return total;
    }

    private static void inputEdge(int n) throws IOException {
        for(int i = 0; i < n; i++) {
            int cnt = readInt();
            while(cnt -- > 0) {
                int city = readInt() - 1;
                graph[i][city] = true;
            }
        }
    }

    private static int solve(int total, int n) {
        int ver = 1, value = total + 1;
        for(int i = 1; i < (1 << n) - 1; i++) {
            int area1 = -1, area2 = -1;
            for(int city = 0; city < n; city++) {
                if(visited[city] == ver) continue;
                if(area1 != -1 && area2 != -1) break;

                if((i & (1 << city)) != 0) {
                    area1 = bfs(city, ver, n, i, true);
                } else {
                    area2 = bfs(city, ver, n, i, false);
                }
            }
            ver++;

            if(area1 == -1 || area2 == -1) continue;
            if(area1 + area2 == total) value = Math.min(value, Math.abs(area1 - area2));
        }

        return value > total ? -1 : value;
    }

    private static int bfs(int cur, int ver, int n, int area, boolean flag) {
        int sum = people[cur];
        visited[cur] = ver;

        int head = 0, tail = 0;
        deque[tail++] = cur;

        while(head < tail) {
            cur = deque[head++];

            for(int i = 0; i < n; i++) {
                if(!graph[cur][i] || visited[i] == ver) continue;
                if(flag == ((area & (1 << i)) != 0)) {
                    sum += people[i];
                    deque[tail++] = i;
                    visited[i] = ver;
                }
            }
        }
        return sum;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
