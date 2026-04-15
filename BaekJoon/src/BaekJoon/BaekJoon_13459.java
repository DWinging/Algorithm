package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_13459 {

    private static class Position {
        int redY, redX, blueY, blueX, turn;

        Position(int redY, int redX, int blueY, int blueX, int turn) {
            this.redY = redY;
            this.redX = redX;
            this.blueY = blueY;
            this.blueX = blueX;
            this.turn = turn;
        }
    }

    final static int MAX_TURN = 10;
    final static int[][] DICT = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Deque<Position> deque = new ArrayDeque<>();
        char[][] map = inputMap(deque, n, m, br);

        System.out.println(bfs(deque, map) ? 1 : 0);
    }

    private static char[][] inputMap(Deque<Position> deque, int n, int m, BufferedReader br) throws IOException{
        char[][] map = new char[n][m];
        int[] red = new int[2];
        int[] blue = new int[2];
        for(int y = 0; y < n; y++) {
            String line = br.readLine();
            for(int x = 0; x < m; x++) {
                char p = line.charAt(x);
                if(p == 'R') {
                    red[0] = y;
                    red[1] = x;
                    map[y][x] = '.';
                    continue;
                }
                if(p == 'B') {
                    blue[0] = y;
                    blue[1] = x;
                    map[y][x] = '.';
                    continue;
                }
                map[y][x] = p;
            }
        }
        deque.add(new Position(red[0], red[1], blue[0], blue[1], 0));
        return map;
    }

    private static boolean bfs(Deque<Position> deque, char[][] map) {
        int n = map.length;
        int m = map[0].length;
        boolean[][][][] visited = new boolean[n][m][n][m];
        Position start = deque.peekFirst();
        visited[start.redY][start.redX][start.blueY][start.blueX] = true;

        while(!deque.isEmpty()) {
            Position p = deque.pollFirst();
            int ry = p.redY;
            int rx = p.redX;
            int by = p.blueY;
            int bx = p.blueX;
            int t = p.turn;

            if(t >= MAX_TURN) continue;

            for(int d = 0; d < DICT.length; d++) {
                int[] red = new int[]{ry, rx, 0};
                int[] blue = new int[]{by, bx, 0};
                if(check(d, red, blue)) {
                    move(d, red, blue,  map);
                    move(d, blue, red, map);
                }
                else {
                    move(d, blue, red, map);
                    move(d, red, blue,  map);
                }
                if(blue[2] == 1) continue;
                if(red[2] == 1) return true;
                if(!visited[red[0]][red[1]][blue[0]][blue[1]]) {
                    deque.add(new Position(red[0], red[1], blue[0], blue[1], t + 1));
                    visited[red[0]][red[1]][blue[0]][blue[1]] = true;
                }

            }
        }
        return false;
    }

    private static boolean check(int d, int[] red, int[] blue) {
        if(d == 0)
            return red[0] > blue[0];
        else if(d == 1)
            return red[0] < blue[0];
        else if(d == 2)
            return red[1] > blue[1];
        else
            return red[1] < blue[1];
    }

    private static void move(int dict, int[] bead1, int[] bead2, char[][] map) {
        int[] d = DICT[dict];
        int y = bead1[0];
        int x = bead1[1];
        while(true) {
            int ny = y + d[0];
            int nx = x + d[1];
            if(map[ny][nx] == '#' || (ny == bead2[0] && nx == bead2[1] && bead2[2] == 0)) break;
            y = ny;
            x = nx;
            if(map[ny][nx] == 'O') {
                bead1[2] = 1;
                break;
            }
        }
        bead1[0] = y;
        bead1[1] = x;
    }
}
