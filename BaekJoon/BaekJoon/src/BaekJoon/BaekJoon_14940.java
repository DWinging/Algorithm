package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_14940 {

    static boolean[][] visit;
    static int[][] matrix;
    static Queue<int[]> que = new LinkedList<>();

    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n][m];
        matrix = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());

                if(matrix[i][j] == 2){
                    que.add(new int[]{i, j});
                    matrix[i][j] = 0;
                    visit[i][j] = true;
                }
            }
        }

        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 1 && !visit[i][j]){
                    sb.append(-1).append(" ");
                }
                else{
                    sb.append(matrix[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void bfs(){

        while(!que.isEmpty()){
            int n1 = que.peek()[0];
            int n2 = que.peek()[1];
            que.poll();

            int[] x_dir = {1, -1, 0, 0};
            int[] y_dir = {0, 0, 1, -1};

            for(int i = 0; i < 4; i++){
                int x = x_dir[i] + n1;
                int y = y_dir[i] + n2;

                if(x >= 0 && y >= 0 && x < n && y < m && !visit[x][y] && matrix[x][y] == 1){
                    que.add(new int[]{x, y});
                    visit[x][y] = true;
                    matrix[x][y] = matrix[n1][n2] + 1;
                }
            }
        }
    }
}
