package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_16933 {
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
        int[][][] visited = new int[n][m][2];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visited[i][j][0] = Integer.MAX_VALUE;
                visited[i][j][1] = Integer.MAX_VALUE;
            }
        }
        visited[0][0][0] = 0;
        visited[0][0][1] = 0;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{0, 0, 1, 1});

        int[][] dict = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while(!deque.isEmpty()) {
            int[] temp = deque.pollFirst();
            int y = temp[0];     // y좌표
            int x = temp[1];     // x좌표
            int l = temp[2];     // 이동 거리
            int day = temp[3];   // 낮(1) or 밤(0)
            int w = visited[y][x][day]; // 부순 벽의 개수

            if(y == n-1 && x == m-1) return l;

            if(day == 0 && visited[y][x][1] > w) {
                visited[y][x][1] = w;
                deque.addLast(new int[]{y, x, l + 1, 1});
            }

            for(int[] d : dict) {
                int ny = y + d[0];
                int nx = x + d[1];
                if(check(ny, nx, n, m)) {
                    if(day == 1) moveAfternoon(deque, visited, map, ny, nx, l, w, k);
                    else moveNight(deque, visited, map, ny, nx, w, l);
                }
            }
        }
        return -1;
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    private static void moveAfternoon(Deque<int[]> deque, int[][][] visited, String[] map, int y, int x, int l, int w, int k) {
        if(map[y].charAt(x) == '0') {
            if(visited[y][x][0] > w) {
                visited[y][x][0] = w;
                deque.addLast(new int[]{y, x, l + 1, 0});
            }
        }
        else {
            if(w + 1 <= k && visited[y][x][0] > w + 1) {
                visited[y][x][0] = w + 1;
                deque.addLast(new int[]{y, x, l + 1, 0});
            }
        }
    }

    private static void moveNight(Deque<int[]> deque, int[][][] visited, String[] map, int y, int x, int w, int l) {
        if(map[y].charAt(x) == '0' && visited[y][x][1] > w) {
            visited[y][x][1] = w;
            deque.addLast(new int[]{y, x, l + 1, 1});
        }
    }
}
