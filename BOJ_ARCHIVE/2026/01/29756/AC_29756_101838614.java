/**
 * [BOJ] 29756 - DDR 체력 관리
 * - 제출 날짜: 2026년 1월 12일
 * - 결과: 맞았습니다!!
 * - 메모리: 11948 KB
 * - 시간: 76 ms
 */

import java.util.*;
import java.io.*;

class Main {

    final static int HP = 100;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] ddp = inputDDP(n, st, br);

        System.out.println(solve(ddp, n, k));
    }

    private static int[][] inputDDP(int n, StringTokenizer st, BufferedReader br) throws IOException {
        int[][] ddp = new int[n][2];

        for(int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                ddp[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        return ddp;
    }

    private static int solve(int[][] ddp, int n, int k) {
        int[] dp = new int[HP + 1];

        for(int i = 0; i < n; i++) {
            recoverHp(dp, k);
            step(ddp, dp, i, k);
        }

        return getMaxValue(dp);
    }

    private static void recoverHp(int[] dp, int k) {
        int fullHP = dp[HP];
        for(int i = HP-1; i >= HP - k; i--) {
            fullHP = Math.max(fullHP, dp[i]);
        }
        dp[HP] = fullHP;

        for(int i = HP - 1; i >= k; i--) {
            dp[i] = Math.max(dp[i], dp[i - k]);
        }

        for(int i = 0; i < k; i++) {
            dp[i] = 0;
        }
    }

    private static void step(int[][] ddp, int[] dp, int idx, int k) {
        int s = ddp[idx][0];
        int h = ddp[idx][1];

        for(int i = 0; i <= HP - h; i++) {
            dp[i] = Math.max(dp[i], s + dp[h + i]);
        }
    }

    private static int getMaxValue(int[] dp) {
        int value = 0;
        for(int i : dp) {
            value = Math.max(value, i);
        }
        return value;
    }
}