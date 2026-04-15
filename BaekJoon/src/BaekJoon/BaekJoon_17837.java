package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_17837 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] board = inputBoard(n, br);
        StringBuilder[][] state = new StringBuilder[n][n];
        Map<Integer, Info> map = new HashMap<>();
        inputState(state, map, k, br);
        System.out.println(startGame(board, state, map));
    }

    private static int[][] inputBoard(int n, BufferedReader br) throws IOException {
        int[][] board = new int[n][n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        return board;
    }

    private static void inputState(StringBuilder[][] state, Map<Integer, Info> map, int k, BufferedReader br) throws IOException {
        StringTokenizer st;

        for(int i = 0; i < state.length; i++) {
            for(int j = 0; j < state[0].length; j++) {
                state[i][j] = new StringBuilder();
            }
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            state[y][x].append(i);
            map.put(i, new Info(y, x, d));
        }
    }

    private static int startGame(int[][] board, StringBuilder[][] state, Map<Integer, Info> map) {
        int[][] dict = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int turn = 0;

        while(turn <= 1000) {
            turn++;
            for(int key = 0; key < map.size(); key++){
                int y = map.get(key).y;
                int x = map.get(key).x;
                int d = map.get(key).dict;
                int idx = map.get(key).idx;
                int dy = y + dict[d][0];
                int dx = x + dict[d][1];
                if(!check(dy, dx, board.length) || (check(dy, dx, board.length) && board[dy][dx] == 2)) {
                    map.get(key).dict += d % 2 == 0 ? 1 : -1;
                    d = map.get(key).dict;
                    dy = y + dict[d][0];
                    dx = x + dict[d][1];
                }

                if(check(dy, dx, board.length) && board[dy][dx] != 2) {
                    StringBuilder s = new StringBuilder(state[y][x].substring(idx));
                    state[y][x].setLength(idx);
                    if(board[dy][dx] == 1) s.reverse();
                    for(int i = 0; i < s.length(); i++) {
                        int temp = s.charAt(i) - '0';
                        map.get(temp).y = dy;
                        map.get(temp).x = dx;
                        map.get(temp).idx = state[dy][dx].length() + i;
                    }
                    state[dy][dx].append(s);
                    if(state[dy][dx].length() >= 4) return turn;
                }
            }
        }

        return -1;
    }

    private static boolean check(int y, int x, int n) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }
}

class Info {
    int y, x, dict, idx = 0;

    Info(int y, int x, int dict) {
        this.y = y;
        this.x = x;
        this.dict = dict;
    }
}