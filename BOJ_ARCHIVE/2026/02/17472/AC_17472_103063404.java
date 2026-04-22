/**
 * [BOJ] 17472 - 다리 만들기 2
 * - 제출 날짜: 2026년 2월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 11580 KB
 * - 시간: 68 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    final static int[][] DICT = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static int[][] map, distMatrix;
    static int[] parents;
    static int c, n, m, idx = 2;

    private static class Edge implements Comparable<Edge> {
        int u, v, w;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.w, e.w);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        inputArray();
        countIsland();
        calculateLength();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        n = readInt();
        m = readInt();
        map = new int[n][m];
    }

    private static void inputArray() throws IOException {
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                map[y][x] = readInt();
            }
        }
    }

    private static void countIsland() {
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(map[y][x] == 1)
                    markingIsland(y, x, idx++);
            }
        }
        distMatrix = new int[idx][idx];
    }

    private static void markingIsland(int s_y, int s_x, int idx) {
        int head = 0, tail = 0;
        int[] dequeY = new int[n * m];
        int[] dequeX = new int[n * m];
        dequeY[tail] = s_y;
        dequeX[tail] = s_x;
        tail++;

        map[s_y][s_x] = idx;
        while(head < tail) {
            int cy = dequeY[head];
            int cx = dequeX[head];
            head++;

            for (int[] d : DICT) {
                int ny = cy + d[0];
                int nx = cx + d[1];
                if (!check(ny, nx)) continue;
                if (map[ny][nx] == 1) {
                    map[ny][nx] = idx;
                    dequeY[tail] = ny;
                    dequeX[tail] = nx;
                    tail++;
                }
            }
        }
    }

    private static void calculateLength() {
        for(int y = 0; y < n; y++) {
            int prev = 0, len = 0;
            for(int x = 0; x < m; x++) {
                int cur = map[y][x];
                if(cur == 0) len++;
                else {
                    if(prev != 0 && prev != cur) {
                        if(len >= 2) {
                            if(distMatrix[prev][cur] == 0 || distMatrix[prev][cur] > len) {
                                distMatrix[prev][cur] = len;
                                distMatrix[cur][prev] = len;
                            }
                        }
                    }
                    prev = cur;
                    len = 0;
                }
            }
        }

        for(int x = 0; x < m; x++) {
            int prev = 0, len = 0;
            for(int y = 0; y < n; y++) {
                int cur = map[y][x];
                if(cur == 0) len++;
                else {
                    if(prev != 0 && prev != cur) {
                        if(len >= 2) {
                            if(distMatrix[prev][cur] == 0 || distMatrix[prev][cur] > len) {
                                distMatrix[prev][cur] = len;
                                distMatrix[cur][prev] = len;
                            }
                        }
                    }
                    prev = cur;
                    len = 0;
                }
            }
        }
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    private static int solve() {
        int len = 0, edgeCount = 0;

        ArrayList<Edge> list = new ArrayList<>();
        for(int i = 2; i < idx; i++) {
            for(int j = i + 1; j < idx; j++) {
                if(distMatrix[i][j] > 1) {
                    list.add(new Edge(i, j, distMatrix[i][j]));
                }
            }
        }

        Collections.sort(list);

        parents = new int[idx];
        for(int i = 2; i < idx; i++) parents[i] = i;

        for(Edge e : list) {
            int u = e.u;
            int v = e.v;
            int w = e.w;
            if(union(u, v)) {
                len += w;
                edgeCount++;
            }
        }

        return edgeCount != idx - 3 ? -1 : len;
    }

    private static boolean union(int n1, int n2) {
        int pa = find(n1);
        int pb = find(n2);

        if(pa == pb) return false;
        parents[pa] = pb;
        return true;
    }

    private static int find(int node) {
        if(parents[node] == node) return node;
        return parents[node] = find(parents[node]);
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
