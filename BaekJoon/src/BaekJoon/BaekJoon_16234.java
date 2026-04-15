package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_16234 {

    final static int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] map = inputPeople(n, br);
        System.out.println(getDays(map, l, r, n));
    }

    private static int[][] inputPeople(int n, BufferedReader br) throws IOException {
        int[][] map = new int[n][n];
        StringTokenizer st;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return map;
    }

    private static int getDays(int[][] map, int l, int r, int n) {
        int day = 0;
        while(true) {
            boolean[][] visited = new boolean[n][n];
            boolean move = false;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(!visited[i][j]) {
                        if(bfs(visited, map, l, r, i, j, n)) move = true;
                    }
                }
            }
            if(!move) break;
            day++;
        }
        return day;
    }

    private static boolean bfs(boolean[][] visited, int[][] map, int l, int r, int y, int x, int n) {
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{y, x});
        visited[y][x] = true;
        int total = 0, idx = 0;

        while(idx < list.size()) {
            int[] temp = list.get(idx++);
            int ry = temp[0];
            int rx = temp[1];
            total += map[ry][rx];

            for(int[] d : DIRECTIONS) {
                int ny = ry + d[0];
                int nx = rx + d[1];
                if(checkDict(ny, nx, n) && !visited[ny][nx] && checkPeople(map[ry][rx], map[ny][nx], l, r)) {
                    list.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }

        openBoundary(list, map, total / list.size());
        return list.size() > 1;
    }

    private static boolean checkDict(int y, int x, int n) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    private static boolean checkPeople(int p1, int p2, int l, int r) {
        int value = Math.abs(p1 - p2);
        return value >= l && value <= r;
    }

    private static void openBoundary(ArrayList<int[]> list, int[][] map, int total) {
        for(int[] temp : list) {
            int y = temp[0];
            int x = temp[1];
            map[y][x] = total;
        }
    }
}
