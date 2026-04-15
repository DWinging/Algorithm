package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_17135 {

    static int[][] map;
    static int[] attacker;
    static int n, m, d;
    static int maxNum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        attacker = new int[3];

        for(int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(maxNum);
    }

    private static void dfs(int idx, int cnt) {
        if(cnt == 3) {
            attack();
            return;
        }
        for(int i = idx; i < m; i++) {
            attacker[cnt] = i;
            dfs(i + 1, cnt + 1);
        }
    }

    private static void attack() {
        Set<int[]> set = new HashSet<>();
        int[][] dict = {{0, -1}, {-1, 0}, {0, 1}};
        int cnt = 0;
        for(int turn = n; turn > 0; turn--) {
            for(int i : attacker) {
                bfs(turn, i, set, dict);
            }
            for(int[] s : set) {
                int y = s[0];
                int x = s[1];
                if(map[y][x] == 1) {
                    map[y][x] = 0;
                    cnt++;
                }
            }
        }

        maxNum = Math.max(maxNum, cnt);

        for(int[] s : set) {
            int y = s[0];
            int x = s[1];
            map[y][x] = 1;
        }
    }

    private static void bfs(int turn, int spot, Set<int[]> set, int[][] dict) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{turn-1, spot});
        boolean[][] visited = new boolean[n][m];
        visited[turn-1][spot] = true;

        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int ry = cur[0];
            int rx = cur[1];

            if(map[ry][rx] == 1) {
                set.add(new int[]{ry, rx});
                break;
            }

            for(int[] d : dict) {
                int ny = d[0] + ry;
                int nx = d[1] + rx;
                if(check(ny, nx) && distance(turn, spot, ny, nx) && !visited[ny][nx]) {
                    deque.addLast(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    private static boolean distance(int y, int x, int ny, int nx) {
        return (Math.abs(ny - y) + Math.abs(nx - x)) <= d;
    }
}
