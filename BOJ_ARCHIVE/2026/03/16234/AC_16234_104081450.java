/**
 * [BOJ] 16234 - 인구 이동
 * - 제출 날짜: 2026년 3월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 12088 KB
 * - 시간: 200 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    final static int BIT_SHIFT = 6;
    final static int[] DIST = {(1 << BIT_SHIFT), -(1 << BIT_SHIFT), 1, -1};
    
    static int[] map, visited, que;
    static int c, N, L, R;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        N = readInt();
        L = readInt();
        R = readInt();
        init();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        map = new int[N << BIT_SHIFT | N];
        visited = new int[N << BIT_SHIFT | N];
        que = new int[N << BIT_SHIFT | N];

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                map[y << BIT_SHIFT | x] = readInt();
            }
        }
    }

    private static int solve() {
        int day = 0;
        boolean flag = true;

        while(flag) {
            flag = false; day++;
            for(int y = 0; y < N; y++) {
                for(int x = 0; x < N; x++) {
                    if(visited[y << BIT_SHIFT | x] < day) {
                        flag |= bfs(y << BIT_SHIFT | x, day);
                    }
                }
            }
        }
        return day - 1;
    }

    private static boolean bfs(int node, int day) {
        int head = 0, tail = 0, sum = map[node];
        que[tail++] = node;
        visited[node] = day;

        while(head < tail) {
            int cur = que[head++];

            for(int d : DIST) {
                int next = cur + d;
                if(check(next) && visited[next] < day && checkPeople(map[cur], map[next])) {
                    que[tail++] = next;
                    visited[next] = day;
                    sum += map[next];
                }
            }
        }

        openBoundary(tail, sum / tail);
        return tail > 1;
    }

    private static boolean check(int cur) {
        int y = cur >> BIT_SHIFT;
        int x = cur & ((1 << BIT_SHIFT) - 1);
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    private static boolean checkPeople(int p1, int p2) {
        int val = Math.abs(p2 - p1);
        return L <= val && val <= R;
    }

    private static void openBoundary(int tail, int value) {
        for(int i = 0; i < tail; i++)
            map[que[i]] = value;
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