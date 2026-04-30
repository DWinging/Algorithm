package BaekJoon;

import java.io.*;

public class BaekJoon_11060 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] step = new int[n];

        String[] temp = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            step[i] = Integer.parseInt(temp[i]);
        }

        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            int jump = step[i];
            if (dp[i] == 0 && i != 0) continue;
            for(int j = i + 1; j < Math.min(i + jump + 1, n); j++) {
                dp[j] = dp[j] == 0 ? dp[i] + 1 : Math.min(dp[j], dp[i] + 1);
            }
        }

        System.out.println(dp[n-1] == 0 && n != 1 ? -1 : dp[n-1]);
    }
}
