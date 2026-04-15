package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BaekJoon_2096 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] maxDp = new int[n+1][3];
        int[][] minDp = new int[n+1][3];

        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());

            maxDp[i][0] = Math.max(maxDp[i-1][0], maxDp[i-1][1]) + num1;
            minDp[i][0] = Math.min(minDp[i-1][0], minDp[i-1][1]) + num1;

            maxDp[i][1] = Math.max(Math.max(maxDp[i-1][0], maxDp[i-1][1]), maxDp[i-1][2]) + num2;
            minDp[i][1] = Math.min(Math.min(minDp[i-1][0], minDp[i-1][1]), minDp[i-1][2]) + num2;

            maxDp[i][2] = Math.max(maxDp[i-1][1], maxDp[i-1][2]) + num3;
            minDp[i][2] = Math.min(minDp[i-1][1], minDp[i-1][2]) + num3;
        }

        int max = Math.max(Math.max(maxDp[n][0], maxDp[n][1]), maxDp[n][2]);
        int min = Math.min(Math.min(minDp[n][0], minDp[n][1]), minDp[n][2]);

        System.out.println(max + " " + min);
    }
}
