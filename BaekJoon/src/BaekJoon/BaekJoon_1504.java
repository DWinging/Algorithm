package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == j) continue;
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[n1][n2] = w;
            graph[n2][n1] = w;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                if(i == k || graph[i][k] == Integer.MAX_VALUE) continue;
                for(int j = 1; j <= n; j++){
                    if(i == j || j == k || graph[k][j] == Integer.MAX_VALUE) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        int result;
        boolean check1 = graph[1][v1] != Integer.MAX_VALUE && graph[v1][v2] != Integer.MAX_VALUE && graph[v2][n] != Integer.MAX_VALUE;
        boolean check2 = graph[1][v2] != Integer.MAX_VALUE && graph[v2][v1] != Integer.MAX_VALUE && graph[v1][n] != Integer.MAX_VALUE;

        if(check1 && check2){
            result = Math.min(graph[1][v1] + graph[v1][v2] + graph[v2][n], graph[1][v2] + graph[v2][v1] + graph[v1][n]);
        }
        else if(check1) {
            result = graph[1][v1] + graph[v1][v2] + graph[v2][n];
        }
        else if(check2){
            result = graph[1][v2] + graph[v2][v1] + graph[v1][n];
        }
        else {
            result = -1;
        }

        System.out.println(result);
    }
}
