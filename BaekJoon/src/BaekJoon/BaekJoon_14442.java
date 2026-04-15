package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_14442 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String[] map = inputMap(n, br);
        System.out.println(bfs(map, n, m, k));
    }

    private static String[] inputMap(int n, BufferedReader br) throws IOException {
        String[] map = new String[n];
        for(int i = 0; i < n; i++) map[i] = br.readLine();
        return map;
    }

    private static int bfs(String[] map, int n, int m, int k) {
        int[][] crash = new int[n][m];
        for(int i = 0; i < n; i++) Arrays.fill(crash[i], Integer.MAX_VALUE);
        crash[0][0] = 0;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{0, 0, 1});

        int[][] dict = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while(!deque.isEmpty()) {
            int[] temp = deque.pollFirst();
            int y = temp[0]; // y좌표
            int x = temp[1]; // w좌표
            int l = temp[2]; // 이동 거리
            int w = crash[y][x]; // 부순 벽의 개수

            if(y == n - 1 && x == m - 1) return l;

            for(int[] d : dict) {
                int ny = y + d[0];
                int nx = x + d[1];
                if(check(ny, nx, n, m)) {
                    if(map[ny].charAt(nx) == '0' && crash[ny][nx] > w) {
                        crash[ny][nx] = w;
                        deque.addLast(new int[] {ny, nx, l + 1});
                    }
                    else if(map[ny].charAt(nx) == '1' && w + 1 <= k && crash[ny][nx] > w + 1){
                        crash[ny][nx] = w + 1;
                        deque.addLast(new int[]{ny, nx, l + 1});
                    }
                }
            }
        }

        return -1;
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}
