package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_14502 {

    final static int WALL = 3;
    static int[][] area;
    static ArrayList<int[]> gas;
    static ArrayList<int[]> emptySpot;
    static int wallCnt, minArea = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        area = new int[n][m];
        gas = new ArrayList<>();
        emptySpot = new ArrayList<>();
        countGasAndWall(br);
        dfs(0, 0);
        System.out.println(n * m - (gas.size() + minArea) - (wallCnt + WALL));
    }

    private static void countGasAndWall(BufferedReader br) throws IOException {
        for(int y = 0; y < area.length; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int x = 0; x < area[y].length; x++) {
                area[y][x] = Integer.parseInt(st.nextToken());
                if(area[y][x] == 0) {
                    emptySpot.add(new int[]{y, x});
                }
                else if(area[y][x] == 1) {
                    wallCnt++;
                }
                else if(area[y][x] == 2) {
                    gas.add(new int[]{y, x});
                }
            }
        }
    }

    private static void dfs(int cnt, int idx) {
        if(cnt == WALL) {
            bfs();
            return;
        }
        for(int i = idx; i < emptySpot.size(); i++) {
            int y = emptySpot.get(i)[0];
            int x = emptySpot.get(i)[1];
            area[y][x] = 1;
            dfs(cnt + 1, i + 1);
            area[y][x] = 0;
        }
    }

    private static void bfs() {
        Deque<int[]> deque = new ArrayDeque<>();
        for(int[] c : gas) {
            deque.addLast(c);
        }
        int[][] dict = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[area.length][area[0].length];

        int areaCnt = 0;
        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int y = cur[0];
            int x = cur[1];

            if(areaCnt > minArea) return;

            for(int[] d : dict) {
                int ny = d[0] + y;
                int nx = d[1] + x;
                if(check(ny, nx, area.length, area[0].length) && area[ny][nx] == 0 && !visited[ny][nx]) {
                    deque.addLast(new int[]{ny, nx});
                    visited[ny][nx] = true;
                    areaCnt++;
                }
            }
        }

        minArea = Math.min(areaCnt, minArea);
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}
