/**
 * [BOJ] 2616 - 소형기관차
 * - 제출 날짜: 2026년 1월 15일
 * - 결과: 맞았습니다!!
 * - 메모리: 18692 KB
 * - 시간: 1956 ms
 */

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);
        int k = Integer.parseInt(br.readLine());

        int total = 2;
        int[][] dp = new int[total + 1][n + 1];

        for(int i = 1; i < n - (k * total) + 1; i++) {
            getValue(dp, arr, n, k, total, i);    
        }
        
        System.out.println(maxOf(dp, n, total));
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

    private static int getValue(int[][] dp, int[] arr, int n, int k, int total, int idx) {
        if(total == 0) return dp[total][idx] = arr[idx + k - 1] - arr[idx - 1];

        int temp = 0;
        for(int i = idx + k; i < n - (k * total) + 2; i++) {
            if(dp[total - 1][i] == 0) {
                temp = getValue(dp, arr, n, k, total - 1, i);    
            }
            else {
                temp = dp[total - 1][i];
            }
            dp[total][idx] = Math.max(temp, dp[total][idx]);
        }
        
        return dp[total][idx] += arr[idx + k - 1] - arr[idx - 1];
    }

    private static int maxOf(int[][] dp, int n, int total) {
        int value = 0;
        for(int i = 0; i <= n; i++) {
            value = Math.max(dp[total][i], value);
        }
        return value;
    }
}