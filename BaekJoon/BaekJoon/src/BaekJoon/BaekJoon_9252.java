package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        int[][] dp = new int[str1.length + 1][str2.length + 1];

        for(int i = 1; i <= str1.length; i++) {
            for(int j = 1; j <= str2.length; j++){
                dp[i][j] = str1[i-1] == str2[j-1] ? dp[i-1][j-1] + 1 : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        int idx1 = str1.length;
        int idx2 = str2.length;
        while(dp[idx1][idx2] != 0) {
            if(dp[idx1][idx2 - 1] == dp[idx1][idx2]) {
                idx2--;
            }
            else if(dp[idx1-1][idx2] == dp[idx1][idx2]) {
                idx1--;
            }
            else {
                sb.append(str2[idx2-1]);
                idx1--;
                idx2--;
            }
        }
        System.out.println(dp[str1.length][str2.length]);
        System.out.println(sb.reverse());
    }
}
