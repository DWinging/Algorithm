package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_15683 {

    private static class Cctv {
        int y, x, num;

        Cctv(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }

    final static int[][] DIRECTION = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Cctv> cctvs = new ArrayList<>();
        int[][] map = new int[n][m];
        int rooms = countEmptyRoom(cctvs, map, n, m, br);

        System.out.println(backtracking(cctvs, map, rooms, 0));
    }

    private static int countEmptyRoom(List<Cctv> cctvs, int[][] map, int n, int m, BufferedReader br) throws IOException {
        StringTokenizer st;
        int rooms = 0;
        for(int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < m; x++) {
                int room = Integer.parseInt(st.nextToken());
                map[y][x] = room;
                if(room == 0)
                    rooms++;
                if(room >= 1 && room <= 5)
                    cctvs.add(new Cctv(y, x, room));
            }
        }
        return rooms;
    }

    private static int backtracking(List<Cctv> cctvs, int[][] map, int rooms, int idx) {
        if(idx >= cctvs.size()) return rooms;
        Deque<int[]> visited = new ArrayDeque<>();
        Cctv cctv = cctvs.get(idx);
        int minValue = rooms;
        int y = cctv.y, x = cctv.x, num = cctv.num;

        if(num == 1) {
            for(int[] d : DIRECTION) {
                int ny = y + d[0];
                int nx = x + d[1];
                int cnt = countRange(visited, map, ny, nx, d[0], d[1]);

                int value = backtracking(cctvs, map, rooms - cnt, idx + 1);
                minValue = Math.min(minValue, value);

                restoreMap(visited, map);
            }
        }
        else if(num == 2) {
            for(int i = 0; i < 2; i++) {
                int cnt = 0;
                for(int j = i; j < 4; j += 2) {
                    int ny = y + DIRECTION[j][0];
                    int nx = x + DIRECTION[j][1];
                    cnt += countRange(visited, map, ny, nx, DIRECTION[j][0], DIRECTION[j][1]);
                }

                int value = backtracking(cctvs, map, rooms - cnt, idx + 1);
                minValue = Math.min(minValue, value);

                restoreMap(visited, map);
            }
        }
        else if(num == 3) {
            for(int i = 0; i < 4; i++) {
                int cnt = 0;
                for(int j = i; j < i + 2; j ++) {
                    int ny = y + DIRECTION[j % 4][0];
                    int nx = x + DIRECTION[j % 4][1];
                    cnt += countRange(visited, map, ny, nx, DIRECTION[j % 4][0], DIRECTION[j % 4][1]);
                }

                int value = backtracking(cctvs, map, rooms - cnt, idx + 1);
                minValue = Math.min(minValue, value);

                restoreMap(visited, map);
            }
        }
        else if(num == 4) {
            for(int i = 0; i < 4; i++) {
                int cnt = 0;
                for(int j = i; j < i + 3; j ++) {
                    int ny = y + DIRECTION[j % 4][0];
                    int nx = x + DIRECTION[j % 4][1];
                    cnt += countRange(visited, map, ny, nx, DIRECTION[j % 4][0], DIRECTION[j % 4][1]);
                }

                int value = backtracking(cctvs, map, rooms - cnt, idx + 1);
                minValue = Math.min(minValue, value);

                restoreMap(visited, map);
            }
        }
        else {
            int cnt = 0;
            for(int[] d : DIRECTION) {
                int ny = y + d[0];
                int nx = x + d[1];
                cnt += countRange(visited, map, ny, nx, d[0], d[1]);
            }

            int value = backtracking(cctvs, map, rooms - cnt, idx + 1);
            minValue = Math.min(minValue, value);

            restoreMap(visited, map);
        }
        return minValue;
    }

    private static int countRange(Deque<int[]> visited, int[][] map, int ny, int nx, int dy, int dx) {
        int cnt = 0;
        while(check(ny, nx, map)) {
            if(map[ny][nx] == 0) {
                map[ny][nx] = 7;
                visited.add(new int[] {ny, nx});
                cnt++;
            }
            ny += dy;
            nx += dx;
        }
        return cnt;
    }

    private static void restoreMap(Deque<int[]> visited, int[][] map) {
        while(!visited.isEmpty()) {
            int[] position = visited.pollFirst();
            map[position[0]][position[1]] = 0;
        }
    }

    private static boolean check(int y, int x, int[][] map) {
        return y >= 0 && y < map.length && x >= 0 && x < map[0].length && map[y][x] != 6;
    }
}
