/**
 * [BOJ] 11559 - Puyo Puyo
 * - 제출 날짜: 2026년 1월 12일
 * - 결과: 맞았습니다!!
 * - 메모리: 11756 KB
 * - 시간: 68 ms
 */

import java.util.*;
import java.io.*;

class Main {

    final static int ROW = 12, COL = 6;
    final static int[][] DICT = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] block = inputBlock(br);

        System.out.println(solve(block));
    }

    private static char[][] inputBlock(BufferedReader br) throws IOException {
        char[][] block = new char[ROW][COL];
        for(int i = ROW-1; i >= 0; i--) {
            String temp = br.readLine();
            for(int j = 0; j < COL; j++) {
                block[i][j] = temp.charAt(j);
            }
        }
        return block;
    }

    private static int solve(char[][] block) {
        boolean check;
        int cnt = 0;
        while(true) {
            check = true;
            for(int i = 0; i < ROW; i++) {
                for(int j = 0; j < COL; j++) {
                    if(block[i][j] != '.' && bfs(block, i, j)) check = false;
                }
            }

            if(check) break;

            cnt++;
            blockDown(block);
        }
        return cnt;
    }

    private static boolean bfs(char[][] block, int y, int x) {
        char temp = block[y][x];
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{y, x});

        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{y, x});
        block[y][x] = '.';

        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int cy = cur[0];
            int cx = cur[1];

            for(int[] d : DICT) {
                int ny = cy + d[0];
                int nx = cx + d[1];

                if(check(ny, nx) && block[ny][nx] == temp) {
                    deque.addLast(new int[]{ny, nx});
                    list.add(new int[]{ny, nx});
                    block[ny][nx] = '.';
                }
            }
        }

        if(list.size() >= 4) {
            return true;
        }
        else {
            for(int[] cur : list) {
                int cy = cur[0];
                int cx = cur[1];
                block[cy][cx] = temp;
            }
            return false;
        }
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < ROW && x >= 0 && x < COL;
    }

    private static void blockDown(char[][] block) {
        Deque<Character> deque = new ArrayDeque<>();
        int y = 0;
        for(int x = 0; x < COL; x++) {
            for(y = 0; y < ROW; y++) {
                if(block[y][x] != '.') {
                    deque.addLast(block[y][x]);
                    block[y][x] = '.';
                } 
            }

            y = 0;
            while(!deque.isEmpty()) {
                block[y++][x] = deque.pollFirst();
            }
        }
    }
}