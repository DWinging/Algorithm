package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_9291 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        int[][] arr = new int[9][9];
        for(int t = 1; t <= testCase; t++) {
            if(t != 1) br.readLine();
            for(int i = 0; i < arr.length; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            String answer = checkRow(arr) && checkCol(arr) && checkBox(arr) ? "CORRECT" : "INCORRECT";
            bw.write("Case " + t + ": " + answer + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static boolean checkRow(int[][] arr) {
        boolean[] visited = new boolean[10];
        boolean cur = true;
        for (int[] y : arr) {
            for (int x : y) {
                if (visited[x] == cur) return false;
                else visited[x] = cur;
            }
            cur = !cur;
        }

        return true;
    }

    private static boolean checkCol(int[][] arr) {
        boolean[] visited = new boolean[10];
        boolean cur = true;
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr.length; y++) {
                if (visited[arr[y][x]] == cur) return false;
                else visited[arr[y][x]] = cur;
            }
            cur = !cur;
        }

        return true;
    }

    private static boolean checkBox(int[][] arr) {
        boolean[] visited = new boolean[10];
        boolean cur = true;

        for(int y = 0; y < arr.length; y += 3) {
            for(int x = 0; x < arr.length; x += 3) {
                for(int i = y; i < y + 3; i++) {
                    for(int j = x; j < x + 3; j++) {
                        if(visited[arr[i][j]] == cur) return false;
                        else visited[arr[i][j]] = cur;
                    }
                }
                cur = !cur;
            }
        }
        return true;
    }
}
