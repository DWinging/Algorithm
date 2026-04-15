package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1520 {
    static int N, M;
    static int[][] visit;
    static int[][] graph;
    static int[] xList = {1, -1, 0, 0};
    static int[] yList = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new int[N][M];
        graph = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                visit[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int r, int c){
        if(r == N-1 && c == M-1){
            return 1;
        }

        if(visit[r][c] != -1){
            return visit[r][c];
        }

        visit[r][c] = 0;
        for(int i = 0; i < 4; i++){
            int x = r + xList[i];
            int y = c + yList[i];
            if(x >= 0 && x < N && y >= 0 && y < M && graph[r][c] > graph[x][y]){
                visit[r][c] += dfs(x, y);
            }
        }

        return visit[r][c];
    }
}
