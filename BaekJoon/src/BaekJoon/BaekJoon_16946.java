package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_16946 {

    final static int[][] DICT = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] map = inputMap(n, br);
        int[][] visited = new int[n][m];
        Map<Integer, Integer> route = countRoute(map, visited, n, m);
        System.out.println(buildString(route, map, visited, n, m));
    }

    private static String[] inputMap(int n, BufferedReader br) throws IOException {
        String[] map = new String[n];
        for(int i = 0; i < n; i++) map[i] = br.readLine();
        return map;
    }

    private static Map<Integer, Integer> countRoute(String[] map, int[][] visited, int n, int m) {
        Map<Integer, Integer> route = new HashMap<>();
        route.put(0, 0);
        int idx = 1;
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(map[y].charAt(x) == '0' && visited[y][x] == 0) {
                    int value = bfs(map, visited, y, x, n, m, idx);
                    route.put(idx, value);
                    idx++;
                }
            }
        }
        return route;
    }

    private static int bfs(String[] map, int[][] visited, int y, int x, int n, int m, int idx) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{y, x});
        visited[y][x] = idx;

        int cnt = 1;
        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int cy = cur[0];
            int cx = cur[1];

            for(int[] d : DICT) {
                int ny = cy + d[0];
                int nx = cx + d[1];
                if(check(ny, nx, n, m) && map[ny].charAt(nx) == '0' && visited[ny][nx] == 0) {
                    deque.addLast(new int[]{ny, nx});
                    visited[ny][nx] = idx;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static String buildString(Map<Integer, Integer> route, String[] map, int[][] visited, int n, int m) {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                int value = map[y].charAt(x) == '0' ? 0 : crashWall(route, visited, y, x, n, m);
                sb.append(value % 10);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static int crashWall(Map<Integer, Integer> route, int[][] visited, int y, int x, int n, int m) {
        Set<Integer> set = new HashSet<>();
        for(int[] d : DICT) {
            int ny = y + d[0];
            int nx = x + d[1];
            if(check(ny, nx, n, m)) {
                set.add(visited[ny][nx]);
            }
        }

        int cnt = 1;
        for(int i : set) {
            cnt += route.get(i);
        }
        return cnt;
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}
