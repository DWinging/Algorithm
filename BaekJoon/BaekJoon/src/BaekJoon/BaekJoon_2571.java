package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2571 {

    final static int BOARD = 100;
    final static int PAPER_SIZE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = inputPaper(n, br);
        calculateWidth(board);
        System.out.println(maxOf(board));
    }

    private static int[][] inputPaper(int n, BufferedReader br) throws IOException {
        StringTokenizer st;
        int[][] board = new int[BOARD + 1][BOARD + 1];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cx = Integer.parseInt(st.nextToken());
            int cy = Integer.parseInt(st.nextToken());
            for(int y = cy; y < cy + PAPER_SIZE; y++) {
                for(int x = cx; x < cx + PAPER_SIZE; x++) {
                    board[y][x] = 1;
                }
            }
        }
        return board;
    }

    private static void calculateWidth(int[][] board) {
        for(int y = 1; y < board.length; y++) {
            for(int x = 1; x < board[y].length; x++) {
                if(board[y][x] != 0) board[y][x] += board[y][x-1];
            }
        }
    }

    private static int maxOf(int[][] board) {
        int area = 0;
        for(int x = 1; x < board.length; x++) {
            for(int y = 1; y < board[x].length; y++) {
                int width = BOARD + 1;
                for(int i = y; i <= BOARD; i++) {
                    width = Math.min(board[i][x], width);
                    if(width == 0) break;
                    area = Math.max(area, width * (i - y + 1));
                }
            }
        }
        return area;
    }
}