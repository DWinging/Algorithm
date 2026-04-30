package BaekJoon;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BaekJoon_11052 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        dp[1] = Integer.parseInt(st.nextToken());

        for(int i = 2; i <= n; i++){
            int num = Integer.parseInt(st.nextToken());
            for(int j = 1; j <= i / 2; j++){
                dp[i] = Math.max(dp[j] + dp[i-j], dp[i]);
            }
            dp[i] = Math.max(dp[i], num);
        }
        System.out.println(dp[n]);
    }
}
