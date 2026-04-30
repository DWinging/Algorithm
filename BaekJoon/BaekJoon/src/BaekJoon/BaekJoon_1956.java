package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1956 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] route = new int[v+1][v+1];
        for(int i = 0; i <= v; i++){
            Arrays.fill(route[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            route[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        for(int k = 1; k <= v; k++){
            for(int i = 1; i <= v; i++){
                if(k == i || route[i][k] == Integer.MAX_VALUE) continue;
                for(int j = 1; j <= v; j++){
                    if(i == j || j == k || route[k][j] == Integer.MAX_VALUE) continue;
                    route[i][j] = Math.min(route[i][j], route[i][k] + route[k][j]);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for(int i = 1; i <= v; i++){
            for(int j = 1; j <= v; j++){
                if(route[i][j] == Integer.MAX_VALUE || route[j][i] ==Integer.MAX_VALUE) continue;
                result = Math.min(route[i][j] + route[j][i], result);
            }
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}
