package BaekJoon;

import java.io.*;

public class BaekJoon_1890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = settingBoard(n, br);
        System.out.println(searchRoute(n, board));
    }

    private static int[][] settingBoard(int n, BufferedReader br) throws IOException{
        int[][] board = new int[n][n];
        for(int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(temp[j]);
            }
        }
        return board;
    }

    private static long searchRoute(int n, int[][] board) {
        long[][] route = new long[n][n];
        route[0][0] = 1;

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                if(route[y][x] == 0L || board[y][x] == 0) continue;
                int w = board[y][x];
                if(y + w < n) route[y + w][x] += route[y][x];
                if(x + w < n) route[y][x + w] += route[y][x];
            }
        }
        return route[n-1][n-1];
    }
}
