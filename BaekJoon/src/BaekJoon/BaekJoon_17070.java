package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] state = inputState(n, br);
        System.out.println(countValue(n, state));
    }

    private static int[][] inputState(int n, BufferedReader br) throws IOException {
        int[][] state = new int[n][n];
        for(int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                state[i][j] = Integer.parseInt(temp[j]);
            }
        }
        return state;
    }

    private static int countValue(int n, int[][] state) {
        int[][] visited = new int[n][n];
        Deque<Pipe> deque = new ArrayDeque<>();
        deque.addLast(new Pipe(0, 1, 0));
        visited[0][1] = 1;

        while(!deque.isEmpty()) {
            Pipe p = deque.pollFirst();
            int x = p.x;
            int y = p.y;
            int dict = p.dict;

            if(dict == 0) {
                checkStraight(n, x, y + 1, 0, state, deque, visited);
                checkDiagonal(n, x + 1, y + 1, 2, state, deque, visited);
            }
            else if (dict == 1) {
                checkStraight(n, x + 1, y, 1, state, deque, visited);
                checkDiagonal(n, x + 1, y + 1, 2, state, deque, visited);
            }
            else {
                checkStraight(n, x, y + 1, 0, state, deque, visited);
                checkStraight(n, x + 1, y, 1, state, deque, visited);
                checkDiagonal(n, x + 1, y + 1, 2, state, deque, visited);
            }
        }

        return visited[n-1][n-1];
    }

    private static void checkStraight(int n, int x, int y, int dict, int[][] state, Deque<Pipe> deque, int[][] visited) {
        if(x < n && y < n && state[x][y] == 0) {
            deque.addLast(new Pipe(x, y, dict));
            visited[x][y]++;
        }
    }

    private static void checkDiagonal(int n, int x, int y, int dict, int[][] state, Deque<Pipe> deque, int[][] visited) {
        if(x < n && y < n && state[x][y - 1] == 0 && state[x - 1][y] == 0 && state[x][y] == 0) {
            deque.addLast(new Pipe(x, y, dict));
            visited[x][y]++;
        }
    }
}

class Pipe {
    int x, y, dict;

    Pipe(int x, int y, int dict) {
        this.x = x;
        this.y = y;
        this.dict = dict;
    }
}