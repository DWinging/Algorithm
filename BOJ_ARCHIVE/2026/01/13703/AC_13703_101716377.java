/**
 * [BOJ] 13703 - 물벼룩의 생존확률
 * - 제출 날짜: 2026년 1월 8일
 * - 결과: 맞았습니다!!
 * - 메모리: 11552 KB
 * - 시간: 68 ms
 */

import java.util.*;
import java.io.*;

class Main {

    final static int[] DICT = {1, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        long[][] dp = new long[k + n + 1][n + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(solve(dp, k, n, 0));
    }

    private static long solve(long[][] dp, int k, int n, int t) {
        dp[k][t] = 0;
        if(k == 0) return 0;
        if(n == t) return dp[k][t] = 1;

        for(int d : DICT) {
            if(dp[k + d][t + 1] == -1) {
                solve(dp, k + d, n, t + 1);
            }
            dp[k][t] += dp[k + d][t + 1];
        }
        
        return dp[k][t];
    }
}