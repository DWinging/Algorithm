package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_26009 {

    final static int[][] DICT = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        boolean[][] road = new boolean[n][m];
        for(int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());

            if(l == 0) road[y][x] = true;
            else checkLine(road, y, x, l, n, m);
        }

        int len = bfs(road, n, m);
        System.out.println(len == -1 ? "NO" : ("YES\n" + len));
    }

    private static void checkLine(boolean[][] road, int y, int x, int l, int n, int m) {
        for(int i = 0; i < l; i++) {
            int dy1 = i;
            int dx1 = l - i;

            int dy2 = l - i;
            int dx2 = i;

            if(check(y + dy1, x + dx1, n, m)) {
                road[y + dy1][x + dx1] = true;
            }
            if(check(y - dy1, x - dx1, n, m)) {
                road[y - dy1][x - dx1] = true;
            }
            if(check(y + dy2, x - dx2, n, m)) {
                road[y + dy2][x - dx2] = true;
            }
            if(check(y - dy2, x + dx2, n, m)) {
                road[y - dy2][x + dx2] = true;
            }
        }
    }

    private static int bfs(boolean[][] road, int n, int m) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[] {0, 0, 0});

        road[0][0] = true;

        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int y = cur[0];
            int x = cur[1];
            int l = cur[2];

            if(y == n - 1 && x == m - 1) return l;

            for(int[] d : DICT) {
                int ny = y + d[0];
                int nx = x + d[1];
                if(check(ny, nx, n, m) && !road[ny][nx]) {
                    deque.addLast(new int[]{ny, nx, l + 1});
                    road[ny][nx] = true;
                }
            }
        }

        return -1;
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}
