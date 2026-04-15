package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_2589 {

    static int N, M, max = 0;
    static int[][] visit;
    static String[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new String[N];

        for(int i = 0; i < N; i++){
            graph[i] =br.readLine();
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(graph[i].charAt(j) == 'L'){
                    visit = new int[N][M];
                    visit[i][j] = 1;
                    bfs(i, j);
                }
            }
        }

        System.out.println(max-1);
    }

    private static void bfs(int r, int c){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{r, c});

        while(!que.isEmpty()){
            int x = que.peek()[0];
            int y = que.peek()[1];

            max = Math.max(max, visit[x][y]);

            que.poll();

            int[] xList = {1, -1, 0, 0};
            int[] yList = {0, 0, 1, -1};
            for(int i = 0; i < 4; i++){
                int tempX = xList[i] + x;
                int tempY = yList[i] + y;
                if(tempX >= 0 && tempX < N && tempY >= 0 && tempY < M && graph[tempX].charAt(tempY) == 'L' && visit[tempX][tempY] == 0){
                    visit[tempX][tempY] = visit[x][y] + 1;
                    que.add(new int[] {tempX, tempY});
                }
            }
        }
    }
}
