package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_2219 {

    static final int INF = 2000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] coms = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(coms[i], INF);
            coms[i][i] = 0;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            coms[c1][c2] = Math.min(w, coms[c1][c2]);
            coms[c2][c1] = Math.min(w, coms[c1][c2]);
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++) {
                if(i == k || coms[i][k] == INF) continue;
                for(int j = 1; j <= n; j++){
                    if(i == j || k == j || coms[k][j] == INF) continue;
                    coms[i][j] = Math.min(coms[i][j], coms[i][k] + coms[k][j]);
                }
            }
        }

        int index = 0;
        int min = INF;
        for(int i = 1; i <= n; i++){
            int sum = 0;
            for(int j = 1; j <= n; j++){
                sum += coms[i][j];
            }
            if(sum < min) {
                index = i;
                min = sum;
            }
        }

        System.out.println(index);
    }
}
