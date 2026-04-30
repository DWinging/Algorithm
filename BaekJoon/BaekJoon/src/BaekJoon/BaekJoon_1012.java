package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1012 {

    static int[][] matrix;

    static int w;
    static int h;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            matrix = new int[w][h];

            for(int j = 0; j < c; j++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                matrix[x][y] = 1;
            }

            int cnt = 0;
            int index = 2;
            for(int j = 0; j < w; j++){
                for(int k = 0; k <matrix[j].length; k++){
                    if(matrix[j][k] == 1){
                        bfs(j, k, index);
                        index += 1;
                        cnt += 1;
                    }
                }
            }

            sb.append(cnt + "\n");
        }

        System.out.println(sb);
    }

    static void bfs(int v, int l, int index){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{v, l});

        while(!que.isEmpty()){
            int n1 = que.peek()[0];
            int n2 = que.peek()[1];
            que.poll();

            int[] x = {1, -1, 0, 0};
            int[] y = {0, 0, 1, -1};

            matrix[n1][n2] = index;

            for(int i = 0; i < 4; i++){
                int temp_x = x[i] + n1;
                int temp_y = y[i] + n2;

                if(temp_x >= 0 && temp_y >= 0 && temp_x < w && temp_y < h){
                    if(matrix[temp_x][temp_y] == 1){
                        que.add(new int[]{temp_x, temp_y});
                    }
                }
            }
        }
    }
}
