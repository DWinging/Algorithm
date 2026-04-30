package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_11053 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] dp = new int[n+1][2];
        for(int i = 1; i <= n; i++){
            dp[i][0] = Integer.parseInt(st.nextToken());
            dp[i][1] = 1;
        }

        int max = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){
                if(dp[j][0] < dp[i][0] ){
                    dp[i][1] = Math.max(dp[j][1] + 1, dp[i][1]);
                }
            }
            max = Math.max(dp[i][1], max);
        }

        System.out.println(max);
    }
}
