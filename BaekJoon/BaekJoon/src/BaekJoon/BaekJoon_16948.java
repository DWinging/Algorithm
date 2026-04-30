package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_16948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        System.out.println(bfs(n, r1, c1, r2, c2));
    }

    private static int bfs(int n, int r1, int c1, int r2, int c2) {
        boolean[][] visited = new boolean[n][n];
        visited[r1][c1] = true;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{r1, c1, 0});

        int[][] dict = {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};
        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int y = cur[0];
            int x = cur[1];
            int cnt = cur[2];

            if(y == r2 && x == c2) return cnt;

            for(int[] d : dict) {
                int ny = y + d[0];
                int nx = x + d[1];
                if(check(ny, nx, n) && !visited[ny][nx]) {
                    deque.addLast(new int[]{ny, nx, cnt + 1});
                    visited[ny][nx] = true;
                }
            }
        }
        return -1;
    }

    private static boolean check(int y, int x, int n) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }
}
