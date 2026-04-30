package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_23268 {

    static final int INF = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] route = new int[n+1][n+1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(route[i], INF);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            route[v1][v2] = h;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                if(i == k || route[i][k] == INF) continue;
                for(int j = 1; j <= n; j++){
                    if(i == j || j == k || route[k][j] == INF) continue;
                    route[i][j] = Math.min(route[i][j], Math.max(route[i][k], route[k][j]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(route[s][e] == INF ? -1 : route[s][e]).append("\n");
        }

        System.out.println(sb);
    }
}
