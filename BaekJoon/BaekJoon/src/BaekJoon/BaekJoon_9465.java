package BaekJoon;

import java.io.*;
import java.util.StringTokenizer;

public class BaekJoon_9465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            int[][] dp = new int[2][n + 1];

            dp[0][1] = Integer.parseInt(st1.nextToken());
            dp[1][1] = Integer.parseInt(st2.nextToken());

            for(int i = 2; i <= n; i++){
                dp[0][i] = Math.max(dp[1][i-2], dp[1][i-1]) + Integer.parseInt(st1.nextToken());
                dp[1][i] = Math.max(dp[0][i-2], dp[0][i-1]) + Integer.parseInt(st2.nextToken());
            }

            sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
        }

        System.out.println(sb);
    }
}
