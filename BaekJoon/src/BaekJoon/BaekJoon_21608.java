package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_21608 {

    final static int[][] DICT = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Map<Integer, Set<Integer>> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        int[][] seat = new int[n][n];
        int friend = 4;

        for(int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            map.put(num, new HashSet<>());
            for(int j = 0; j < friend; j++) {
                map.get(num).add(Integer.parseInt(st.nextToken()));
            }

            assignSeat(map, seat, num, n);
        }

        int total = 0;
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                total += countSeat(map.get(seat[y][x]), seat, y, x, n);
            }
        }
        bw.write(String.valueOf(total));
        bw.flush();
        bw.close();
    }

    private static void assignSeat(Map<Integer, Set<Integer>> map, int[][] seat, int num, int n) {
        Set<Integer> set = map.get(num);
        Info info = new Info();
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                if(seat[y][x] == 0) checkSide(set, seat, y, x, num, n, info);
            }
        }

        seat[info.y][info.x] = num;
    }

    private static void checkSide(Set<Integer> set, int[][] seat, int y, int x, int num, int n, Info info) {
        int empty = 0, like = 0;
        for(int[] d : DICT) {
            int ny = y + d[0];
            int nx = x + d[1];
            if(check(ny, nx, n)) {
                int temp = seat[ny][nx];
                if(temp == 0) empty++;
                else if(set.contains(temp)) like++;
            }
        }

        if((like > info.like) || (like == info.like && empty > info.empty)) {
            info.y = y;
            info.x = x;
            info.like = like;
            info.empty = empty;
        }
    }

    private static int countSeat(Set<Integer> set, int[][] seat, int y, int x, int n) {
        int cnt = 0;
        for(int[] d : DICT) {
            int ny = y + d[0];
            int nx = x + d[1];
            if(check(ny, nx, n) && set.contains(seat[ny][nx])) cnt++;
        }
        return cnt == 0 ? 0 : (int)Math.pow(10, cnt - 1);
    }

    private static boolean check(int y, int x, int n) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    private static class Info {
        int y = -1, x = -1, empty = -1, like = -1;

        Info() {

        }
    }
}
