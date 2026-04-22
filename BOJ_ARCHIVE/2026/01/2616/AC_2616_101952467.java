/**
 * [BOJ] 2616 - 소형기관차
 * - 제출 날짜: 2026년 1월 15일
 * - 결과: 맞았습니다!!
 * - 메모리: 18764 KB
 * - 시간: 152 ms
 */

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);
        int k = Integer.parseInt(br.readLine());

        int total = 3;
        int[][] dp = new int[total + 1][n + 1];
        
        System.out.println(maxOf(dp, arr, n, k, total));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            arr[i] += arr[i-1];
        }
        return arr;
    }

    private static int maxOf(int[][] dp, int[] arr, int n, int k, int total) {
        for(int i = 1; i <= total; i++) {
            for(int j = k; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j-1], Math.max(dp[i][j], dp[i-1][j - k] + (arr[j] - arr[j - k])));
            }
        }
        
        return dp[total][n];
    }
}