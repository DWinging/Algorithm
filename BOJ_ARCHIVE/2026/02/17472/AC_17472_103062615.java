/**
 * [BOJ] 17472 - 다리 만들기 2
 * - 제출 날짜: 2026년 2월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 18080 KB
 * - 시간: 184 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    final static int[][] DICT = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static ArrayList<int[]> list = new ArrayList<>();
    static int[][] map, bridge;
    static int[] dequeY, dequeX, parents;
    static int c, n, m;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        n = readInt();
        m = readInt();

        init();
        inputArray();
        int total = countIsland();
        calculateLength(total);
        System.out.println(solve(total + 2));
    }

    private static void init() {
        map = new int[n][m];
        dequeY = new int[n * m];
        dequeX = new int[n * m];
    }

    private static void inputArray() throws IOException {
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                map[y][x] = readInt();
            }
        }
    }

    private static int countIsland() {
        int idx = 2;
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(map[y][x] == 1) markingIsland(y, x, idx++);
            }
        }
        return idx - 2;
    }

    private static void markingIsland(int s_y, int s_x, int idx) {
        int head = 0, tail = 0;
        dequeY[tail] = s_y;
        dequeX[tail] = s_x;
        tail++;

        map[s_y][s_x] = idx;
        while(head < tail) {
            int cy = dequeY[head];
            int cx = dequeX[head];
            head++;

            for(int[] d : DICT) {
                int ny = cy + d[0];
                int nx = cx + d[1];
                if(check(ny, nx) && map[ny][nx] == 1) {
                    map[ny][nx] = idx;
                    dequeY[tail] = ny;
                    dequeX[tail] = nx;
                    tail++;
                }
            }
        }
    }

    private static void calculateLength(int cnt) {
        bridge = new int[cnt + 2][cnt + 2];
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(map[y][x] != 0) {
                    buildBridge(y, x, map[y][x]);
                }
            }
        }
    }

    private static void buildBridge(int y, int x, int idx) {
        for(int[] d : DICT) {
            int ny = y + d[0];
            int nx = x + d[1];
            int len = 0, land = -1;
            while(check(ny, nx)) {
                int next = map[ny][nx];
                if(next == idx) break;
                if(next != 0) {
                    land = next;
                    break;
                }
                ny += d[0];
                nx += d[1];
                len++;
            }
            if(land != -1 && len > 1) {
                if(bridge[idx][land] == 0 || bridge[idx][land] > len) {
                    bridge[idx][land] = len;
                    bridge[land][idx] = len;
                    list.add(new int[]{idx, land, len});
                }
            }
        }
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    private static int solve(int cnt) {
        int len = 0, edgeCount = 0;

        parents = new int[cnt];
        for(int i = 2; i < cnt; i++) parents[i] = i;

        list.sort((l1, l2) -> Integer.compare(l1[2], l2[2]));

        for(int[] l : list) {
            int n1 = l[0];
            int n2 = l[1];
            int n3 = l[2];
            if(union(n1, n2)) {
                len += n3;
                edgeCount++;
            }
        }

        return edgeCount != cnt - 3 ? -1 : len;
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
