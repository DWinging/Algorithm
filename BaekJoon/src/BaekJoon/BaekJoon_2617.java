package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2617 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] bead = new int[n+1][n+1];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            bead[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                if(i == k) continue;
                for(int j = 1; j <= n; j++){
                    if(i == j || j == k) continue;
                    if(bead[i][k] == 1 && bead[k][j] == 1){
                        bead[i][j] = 1;
                    }
                }
            }
        }

        int half = n / 2;
        int total = 0;
        for(int i = 1; i <= n; i++){
            int left = 0;
            int right = 0;
            for(int j = 1; j <= n; j++){
                left += bead[i][j];
                right += bead[j][i];
            }
            if(left > half || right > half) total++;
        }

        System.out.println(total);
    }
}
