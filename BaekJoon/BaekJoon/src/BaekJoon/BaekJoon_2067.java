package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2067 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int coin, money, testCase = Integer.parseInt(br.readLine());
        int[] dp;
        while(testCase-- > 0){
            br.readLine();
            st =  new StringTokenizer(br.readLine());
            money = Integer.parseInt(br.readLine());
            dp = new int[money + 1];
            dp[0] = 1;
            while(st.hasMoreTokens()){
                coin = Integer.parseInt(st.nextToken());
                for(int j = coin; j <= money; j++){
                    dp[j] += dp[j-coin];
                }
            }
            sb.append(dp[money]).append("\n");
        }
        System.out.println(sb);
    }
}
