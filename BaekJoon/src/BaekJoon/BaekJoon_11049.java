package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][n];
        StringTokenizer st;

        int[] matrix = new int[n + 1];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = Integer.parseInt(st.nextToken());
            matrix[i+1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 2; i < n + 1; i++){
            for(int j = 0; j < n - i + 1; j++){
                int temp = i + j - 1;
                dp[j][temp] = Integer.MAX_VALUE;
                for(int k = j; k < temp; k++){
                    int value = dp[j][k] + dp[k+1][temp] + matrix[j] * matrix[k + 1] * matrix[i + j];
                    dp[j][temp] = Math.min(dp[j][temp], value);
                }
            }
        }

        System.out.println(dp[0][n-1]);
    }
}
