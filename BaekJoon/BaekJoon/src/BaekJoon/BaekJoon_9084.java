package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int textCase = Integer.parseInt(br.readLine());
        int coin, money;
        int[][] dp;
        int[] coins;

        while(textCase-- > 0) {
            coin = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            coins = new int[coin + 1];
            for(int i = 1; i <= coin; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }
            money = Integer.parseInt(br.readLine());
            dp = new int[coin + 1][money + 1];

            for(int i = 1; i <= coin; i++){
                dp[i][0] = 1;
                for(int j = 1; j <= money; j++){
                    if(j - coins[i] < 0){
                        dp[i][j] = dp[i-1][j];
                        continue;
                    }
                    dp[i][j] = dp[i-1][j] + dp[i][j - coins[i]];
                }
            }
            sb.append(dp[coin][money]).append("\n");
        }
        System.out.println(sb);
    }
}
