package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_19952 {

    private static int[][] DICT = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int[] s = new int[2];
            s[0] = Integer.parseInt(st.nextToken()) - 1;
            s[1] = Integer.parseInt(st.nextToken()) - 1;

            int[] e = new int[2];
            e[0] = Integer.parseInt(st.nextToken()) - 1;
            e[1] = Integer.parseInt(st.nextToken()) - 1;

            int[][] map = new int[w][h];
            for(int i = 0; i < o; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                int l = Integer.parseInt(st.nextToken());
                map[y][x] = l;
            }

            bw.write((bfs(map, w, h, f, s, e) ? "잘했어!!" : "인성 문제있어??") + "\n");
        }
        bw.flush();
        bw.close();


    }

    private static boolean bfs(int[][] map, int w, int h, int f, int[] s, int[] e) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{s[0], s[1], f});

        boolean[][] visited = new boolean[w][h];
        visited[s[0]][s[1]] = true;

        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int cy = cur[0];
            int cx = cur[1];
            int p = cur[2];

            if(cy == e[0] && cx == e[1]) return true;
            if(p == 0) continue;

            for(int[] d : DICT) {
                int ny = cy + d[0];
                int nx = cx + d[1];
                if(check(ny, nx, w, h) && !visited[ny][nx] && p >= (map[ny][nx] - map[cy][cx])) {
                    deque.addLast(new int[]{ny, nx, p - 1});
                    visited[ny][nx] = true;
                }
            }
        }

        return false;
    }

    private static boolean check(int y, int x, int w, int h) {
        return y >= 0 && y < w && x >= 0 && x < h;
    }
}
