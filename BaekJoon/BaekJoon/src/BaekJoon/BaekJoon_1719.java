package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BaekJoon_1719 {

    static int[][] visit, graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visit = new int[n+1][n+1];
        graph = new int[n+1][n+1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(graph[i], 10001);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            visit[v1][v2] = v2;
            visit[v2][v1] = v1;
            graph[v1][v2] = w;
            graph[v2][v1] = w;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                if(i == k) continue;
                for(int j = 1; j <= n; j++){
                    if(i == j || j == k) continue;
                    if(graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        visit[i][j] = visit[i][k];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == j) sb.append("-");
                else sb.append(visit[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
