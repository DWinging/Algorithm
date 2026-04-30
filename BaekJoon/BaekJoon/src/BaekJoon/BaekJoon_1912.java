package BaekJoon;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BaekJoon_1912 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        int max = -1001;
        dp[0] = -1001;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            int num = Integer.parseInt(st.nextToken());
            dp[i] = Math.max(dp[i - 1] + num, num);
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }
}
