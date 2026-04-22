/**
 * [BOJ] 16432 - 떡장수와 호랑이
 * - 제출 날짜: 2026년 4월 15일
 * - 결과: 맞았습니다!!
 * - 메모리: 12160 KB
 * - 시간: 72 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int[][] arr;
    static int[] res;
    static boolean[][] visited;
    static int c;
    static boolean flag = false;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);

        res = new int[n];
        visited = new boolean[n][11];
        dfs(0, 0, n);
        System.out.print(flag ? buildString() : -1);
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n][11];        
        for(int i = 0; i < n; i++) {
            int m = readInt();
            arr[i][0] = m;
            for(int day = 1; day <= m; day++) {
                arr[i][day] = readInt();
            }
        }
    }

    private static void dfs(int day, int pre, int n) {
        if(day == n) {
            flag = true;
            return;
        }
        
        for(int i = 1; i <= arr[day][0]; i++) {        
            int num = arr[day][i];
            
            if(num == pre || visited[day][num]) continue;
            
            res[day] = num;
            dfs(day + 1, num, n);
            if(flag) break;
            visited[day][num] = true;
        }
    }

    private static String buildString() {
        StringBuilder sb = new StringBuilder();
        for(int i : res) {
            sb.append(i).append('\n');
        }
        return sb.toString();
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}