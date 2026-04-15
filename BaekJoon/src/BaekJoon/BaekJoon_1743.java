package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1743 {

    static int N, M, K;
    static boolean[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visit = new boolean[N][M];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
        }

        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] && !visit[i][j]){
                    visit[i][j] = true;
                    cnt = Math.max(cnt, bfs(i, j));
                }
            }
        }

        System.out.println(cnt);
    }

    public static int bfs(int r, int c){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{r, c});

        int cnt = 1;
        while(!que.isEmpty()){
            int row = que.peek()[0];
            int col = que.peek()[1];
            que.poll();

            int[] xList = {1, -1, 0, 0};
            int[] yList = {0, 0, 1, -1};

            for(int i = 0; i < 4; i++){
                int x = row + xList[i];
                int y = col + yList[i];

                if(x >= 0 && x < N && y >= 0 && y < M && map[x][y] && !visit[x][y]){
                    visit[x][y] = true;
                    que.add(new int[]{x, y});
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
