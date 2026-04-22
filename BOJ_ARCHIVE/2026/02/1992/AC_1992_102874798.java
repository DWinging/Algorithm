/**
 * [BOJ] 1992 - 쿼드트리
 * - 제출 날짜: 2026년 2월 12일
 * - 결과: 맞았습니다!!
 * - 메모리: 12080 KB
 * - 시간: 60 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] arr = inputArray(n, br);

        char result = checkDots(arr, 0, n, 0, n);
        if(result == '2') solve(arr, 0, n, 0, n);
        else sb.append(result);
        System.out.println(sb);
    }

    private static char[][] inputArray(int n, BufferedReader br) throws IOException {
        char[][] arr = new char[n][n];
        for(int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        return arr;
    }

    private static void solve(char[][] arr, int s_y, int e_y, int s_x, int e_x) {
        sb.append('(');

        int mid_y = (s_y + e_y) / 2;
        int mid_x = (s_x + e_x) / 2;

        // 1구간
        char result = checkDots(arr, s_y, mid_y, s_x, mid_x);
        if(result == '2') solve(arr, s_y, mid_y, s_x, mid_x);
        else sb.append(result);
        
        // 2구간
        result = checkDots(arr, s_y, mid_y, mid_x, e_x);
        if(result == '2') solve(arr, s_y, mid_y, mid_x, e_x);
        else sb.append(result);

        // 3구간
        result = checkDots(arr, mid_y, e_y, s_x, mid_x);
        if(result == '2') solve(arr, mid_y, e_y, s_x, mid_x);
        else sb.append(result);

        // 4구간
        result = checkDots(arr, mid_y, e_y, mid_x, e_x);
        if(result == '2') solve(arr, mid_y, e_y, mid_x, e_x);
        else sb.append(result);
        sb.append(')');
    }

    private static char checkDots(char[][] arr, int s_y, int e_y, int s_x, int e_x) {
        char bit = arr[s_y][s_x];

        for(int y = s_y; y < e_y; y++) {
            for(int x = s_x; x < e_x; x++) {
                if(arr[y][x] != bit) return '2';
            }
        }
        return bit;
    }
}
