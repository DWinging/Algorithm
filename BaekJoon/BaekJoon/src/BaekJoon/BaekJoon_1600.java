package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1600 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] map = inputMap(n, m, br);
        System.out.println(bfs(map, n, m, k));
    }

    private static int[][] inputMap(int n, int m, BufferedReader br) throws IOException {
        int[][] map = new int[n][m];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return map;
    }

    private static int bfs(int[][] map, int n, int m, int k) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{0, 0, 0, 0});

        int[][] visited = new int[n][m];
        for (int[] row : visited) Arrays.fill(row, Integer.MAX_VALUE);
        visited[0][0] = 0;

        int[][] jump = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};
        int[][] dict = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while(!deque.isEmpty()) {
            int[] temp = deque.pollFirst();
            int y = temp[0];
            int x = temp[1];
            int move = temp[2];
            int cnt = temp[3];

            if(y == n-1 && x == m-1) return move;

            if(cnt < k) {
                for(int[] j : jump) {
                    int ny = y + j[0];
                    int nx = x + j[1];
                    if(check(ny, nx, n, m) && map[ny][nx] == 0 && visited[ny][nx] > cnt + 1) {
                        deque.add(new int[] {ny, nx, move + 1, cnt + 1});
                        visited[ny][nx] = cnt + 1;
                    }
                }
            }

            for(int[] d : dict) {
                int ny = y + d[0];
                int nx = x + d[1];
                if(check(ny, nx, n, m) && map[ny][nx] == 0 && visited[ny][nx] > cnt) {
                    deque.add(new int[] {ny, nx, move + 1, cnt});
                    visited[ny][nx] = cnt;
                }
            }
        }

        return -1;
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}
