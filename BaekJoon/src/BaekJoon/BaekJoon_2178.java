package BaekJoon;


import java.util.*;
import java.io.*;

public class BaekJoon_2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[] mero = new String[n];
        for(int i = 0; i < n; i++) {
            mero[i] = br.readLine();
        }

        System.out.println(bfs(n, m, mero));
    }

    private static int bfs(int n, int m, String[] mero) {
        int[][] visited = new int[n][m];
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{0, 0});
        visited[0][0] = 1;
        int[][] dict = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int ry = cur[0];
            int rx = cur[1];
            if(ry == n-1 && rx == m-1) break;

            for(int[] d : dict) {
                int ny = d[0] + ry;
                int nx = d[1] + rx;
                if(check(ny, nx, n, m) && mero[ny].charAt(nx) == '1' && visited[ny][nx] == 0) {
                    deque.addLast(new int[]{ny, nx});
                    visited[ny][nx] = visited[ry][rx] + 1;
                }
            }
        }

        return visited[n-1][m-1];
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}
