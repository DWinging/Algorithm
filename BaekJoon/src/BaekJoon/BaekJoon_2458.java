package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_2458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] students = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(students[i], 0);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            students[num1][num2] = 1;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                if(i == k) continue;
                for(int j = 1; j <= n; j++){
                    if(j == i || j == k) continue;
                    if(students[i][j] == 1 || (students[i][k] == 1 && students[k][j] == 1)) {
                        students[i][j] = 1;
                    }
                }
            }
        }

        int cnt, total = 0;
        for(int i = 1; i <= n; i++){
            cnt = 0;
            for(int j = 1; j <= n; j++){
                cnt += students[i][j] + students[j][i];
            }
            if(cnt == n-1) total++;
        }
        System.out.println(total);
    }
}
